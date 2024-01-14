package com.example.geminiapp.local.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geminiapp.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "gemini-database"
            ).build(
            )
            INSTANCE = instance
            instance
        }
    }

    @Provides
    @Singleton
    fun provideChatEntityDao(database: AppDatabase) = database.chatEntityDao()
}