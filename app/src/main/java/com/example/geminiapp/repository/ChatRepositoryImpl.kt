package com.example.geminiapp.repository

import com.example.geminiapp.domain.Chat
import com.example.geminiapp.local.database.dao.ChatEntityDao
import com.example.geminiapp.local.database.entity.ChatEntity
import com.example.geminiapp.local.database.entity.toChatList
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatEntityDao: ChatEntityDao,
    private val generativeModel: GenerativeModel,
) : ChatRepository {
    override suspend fun send(message: String) {
        withContext(Dispatchers.IO) {

            chatEntityDao.insert(
                ChatEntity.fromChat(
                    Chat(
                        id = UUID.randomUUID().toString(),
                        name = "User",
                        message = message,
                        isUser = true,
                    )
                )
            )

            val response = generativeModel.generateContent(message)

            chatEntityDao.insert(
                ChatEntity.fromChat(
                    Chat(
                        id = UUID.randomUUID().toString(),
                        name = "Gemini Pro",
                        message = response.text ?: "",
                        isUser = false,
                    )
                )
            )
        }
    }

    override fun observeAll(): Flow<List<Chat>> = flow {
        chatEntityDao.observeAll().collect { emit(it.toChatList()) }
    }.flowOn(Dispatchers.IO)
}
