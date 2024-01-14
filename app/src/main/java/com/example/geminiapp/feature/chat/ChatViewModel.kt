package com.example.geminiapp.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminiapp.domain.Chat
import com.example.geminiapp.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private data class ChatViewModelState(
    val message: String = "",
    val sending: Boolean = false,
    val isLoading: Boolean = false,
)

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val vmState = MutableStateFlow(ChatViewModelState())

    val uiState: StateFlow<ChatUiState> = combine(
        vmState,
        chatRepository.observeAll()
    ) { state, chatList -> convertToUiState(state, chatList) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = convertToUiState(ChatViewModelState(), emptyList())
        )

    fun changeMessage(message: String) {
        vmState.update { it.copy(message = message) }
    }

    fun sendMessage() {
        vmState.update { it.copy(message = "", sending = true) }
        viewModelScope.launch {
            chatRepository.send(vmState.value.message)
            vmState.update { it.copy(sending = false) }
        }
    }

    private fun convertToUiState(
        state: ChatViewModelState,
        chatList: List<Chat>
    ): ChatUiState {
        return ChatUiState(
            message = state.message,
            sending = state.sending,
            isLoading = state.isLoading,
            chatList = chatList,
        )
    }

}