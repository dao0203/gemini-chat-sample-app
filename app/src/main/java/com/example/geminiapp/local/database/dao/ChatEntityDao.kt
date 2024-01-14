package com.example.geminiapp.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.geminiapp.local.database.entity.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chatEntity: ChatEntity)

    @Query("SELECT * FROM chat")
    fun observeAll(): Flow<List<ChatEntity>>

}
