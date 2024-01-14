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

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    @Provides
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
}