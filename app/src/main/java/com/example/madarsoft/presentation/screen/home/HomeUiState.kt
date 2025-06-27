package com.example.madarsoft.presentation.screen.home

import com.example.madarsoft.domain.enitiy.User

/**
 * Created by Aziza Helmy on 27/06/2025.
 */
data class HomeUiState(
    val user: UserUiState = UserUiState(),
    val isLoading: Boolean = true,
    val error: String = ""
)

data class UserUiState(
    val id: Long = 0,
    val name: String = "",
    val age: String = "",
    val title: String = "",
    val job: String = "",
    val gender: String = "",
    //val isValidData: Boolean = true
) {
    val isValidData: Boolean
        get() = name.isNotBlank() &&
                title.isNotBlank() &&
                job.isNotBlank() &&
                gender.isNotBlank() &&
                age.toIntOrNull() != null
}


fun UserUiState.toUser() =
    User(id = id, name = name, age = age.toInt(), title = title, job = job, gender = gender)