package com.example.mobile_computing.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val useImgUrl: Boolean = false,
    val imageUrl: String? = null,
    val imageFileName: String? = null
)
