package com.example.mobile_computing.data.conversation_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val name: String,
    val profileUrl: String? = null
)