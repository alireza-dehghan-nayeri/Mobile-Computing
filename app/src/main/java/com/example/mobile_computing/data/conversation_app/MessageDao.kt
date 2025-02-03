package com.example.mobile_computing.data.conversation_app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert
    suspend fun insertMessage(message: MessageEntity)

    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<MessageEntity>>
}

