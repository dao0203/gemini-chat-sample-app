package com.example.geminiapp.repository

import com.example.geminiapp.domain.Chat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun send(
        message: String,
    )

    fun observeAll(): Flow<List<Chat>>
}