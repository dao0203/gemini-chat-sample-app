package com.example.geminiapp.feature.chat

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.geminiapp.domain.Chat

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    ChatContent(
        uiState = uiState.value,
        onMessageChange = viewModel::changeMessage,
        onSendClick = viewModel::sendMessage,
    )
}

@Composable
private fun ChatContent(
    uiState: ChatUiState,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextField(
                    value = uiState.message,
                    onValueChange = onMessageChange,
                    modifier = Modifier.weight(1f),
                )
                if (uiState.sending)
                    CircularProgressIndicator()
                else
                    IconButton(onClick = onSendClick) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send",
                        )
                    }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn {
                items(
                    uiState.chatList.size,
                    key = { index -> uiState.chatList[index].id }
                ) { index ->
                    ChatItem(
                        chat = uiState.chatList[index],
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ChatItem(
    chat: Chat,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        if (chat.isUser) {
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = chat.message)
                }
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "User icon",
                )
            }
        } else {
            Row {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "User icon",
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = chat.message)
                }
            }
        }
    }
}
