package com.example.geminiapp.api.di

import com.example.geminiapp.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideGeminiApi(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.apiKey,
        )
    }
}