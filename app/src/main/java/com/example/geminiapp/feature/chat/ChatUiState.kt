package com.example.geminiapp.feature.chat

import com.example.geminiapp.domain.Chat


data class ChatUiState(
    val message: String = "",
    val sending: Boolean = false,
    val isLoading: Boolean = false,
    val chatList: List<Chat> = emptyList(),
)
