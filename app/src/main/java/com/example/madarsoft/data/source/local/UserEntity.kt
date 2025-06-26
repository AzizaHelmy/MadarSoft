package com.example.madarsoft.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Aziza Helmy on 26/06/2025.
 */

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val age: Int,
    val title: String,
    val gender: String
)