package com.example.madarsoft.domain.enitiy

/**
 * Created by Aziza Helmy on 27/06/2025.
 */

data class User(
    val id: Long,
    val name: String,
    val age: Int,
    val title: String,
    val job: String,
    val gender: String
)