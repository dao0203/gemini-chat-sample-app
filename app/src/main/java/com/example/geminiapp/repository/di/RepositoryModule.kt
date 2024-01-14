package com.example.geminiapp.repository.di

import com.example.geminiapp.repository.ChatRepository
import com.example.geminiapp.repository.ChatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository

}
