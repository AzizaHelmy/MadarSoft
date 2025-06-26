package com.example.madarsoft.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madarsoft.domain.repo.MadarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 26/06/2025.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(val repo: MadarRepository) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState())
    val state = _state.asStateFlow()

    fun onNameChanged(newName: String) {
        _state.update { it.copy(user = it.user.copy(name = newName)) }
    }


    fun onAgeChanged(age: Int) {
        _state.update { it.copy(user = it.user.copy(age = age)) }
    }

    fun onTitleChanged(title: String) {
        _state.update { it.copy(user = it.user.copy(title = title)) }
    }

    fun onJobChanged(job: String) {
        _state.update { it.copy(user = it.user.copy(job = job)) }
    }

    fun addUser(user: UserUiState) {
        try {
            _state.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                repo.addUser(user.toUser())
            }
        } catch (_: Exception) {
            _state.update { it.copy(error = "Failed to add user") }
        }
    }
}