package com.example.geminiapp.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.geminiapp.domain.Chat

@Entity(tableName = "chat")
data class ChatEntity(
    @PrimaryKey val id: String,
    val name: String,
    val message: String,
    val isUser: Boolean,
) {
    fun toChat(): Chat {
        return Chat(
            id = id,
            name = name,
            message = message,
            isUser = isUser,
        )
    }

    companion object {
        fun fromChat(chat: Chat): ChatEntity {
            return ChatEntity(
                id = chat.id,
                name = chat.name,
                message = chat.message,
                isUser = chat.isUser,
            )
        }
    }
}

fun List<ChatEntity>.toChatList(): List<Chat> {
    return map { chatEntity ->
        chatEntity.toChat()
    }
}
