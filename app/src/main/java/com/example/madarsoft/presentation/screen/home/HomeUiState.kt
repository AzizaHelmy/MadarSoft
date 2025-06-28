package com.example.madarsoft.presentation.screen.home

import com.example.madarsoft.domain.enitiy.User
import com.example.madarsoft.presentation.screen.component.Gender

/**
 * Created by Aziza Helmy on 27/06/2025.
 */
data class HomeUiState(
    val user: UserUiState = UserUiState(),
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
    val showDialog: Boolean = false

)

data class UserUiState(
    val id: Long = 0,
    val name: String = "",
    val age: String = "",
    val title: String = "",
    val job: String = "",
    val gender: Gender? = null
) {
    val isValidData: Boolean
        get() = name.isNotBlank()
                && name.length <= 50
                && title.isNotBlank()
                && title.length <= 50
                && job.isNotBlank()
                && gender != null
                && age.toIntOrNull()?.let { it in 0..120 } == true
}

fun UserUiState.toUser() =
    User(
        id = id,
        name = name,
        age = age.toInt(),
        title = title,
        job = job,
        gender = gender?.name ?: throw IllegalStateException("Gender is required")
    )
