package com.example.madarsoft.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madarsoft.domain.repo.MadarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Aziza Helmy on 26/06/2025.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(val repo: MadarRepository) : ViewModel() {

    private val _state = MutableStateFlow<DetailsUiState>(DetailsUiState())
    val state = _state.asStateFlow()

    init {
        getUserData()
    }

    private fun getUserData() {
        try {
            _state.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                val result = repo.getUser()
                _state.update {
                    it.copy(
                        isLoading = false,
                        user = it.user.copy(
                            name = result.name,
                            age = result.age.toString(),
                            title = result.title,
                            job = result.job,
                            gender = result.gender
                        )
                    )
                }
            }
        } catch (_: Exception) {
            _state.update { it.copy(error = "Failed to get user") }
        }

    }
}