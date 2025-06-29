package com.example.madarsoft.presentation.screen.details

/**
 * Created by Aziza Helmy on 27/06/2025.
 */
data class DetailsUiState(
    val user: UserUiState = UserUiState(),
    val isLoading: Boolean = true,
    val error: String = ""
)

data class UserUiState(
    val name: String = "",
    val age: String = "",
    val title: String = "",
    val job: String = "",
    val gender: String = "",
)