package com.example.geminiapp.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.geminiapp.local.database.dao.ChatEntityDao
import com.example.geminiapp.local.database.entity.ChatEntity

@Database(entities = [ChatEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun chatEntityDao(): ChatEntityDao
}
