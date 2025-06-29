package com.example.madarsoft.domain.usecase

import com.example.madarsoft.domain.enitiy.User
import com.example.madarsoft.domain.repo.MadarRepository
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 29/06/2025.
 */

class AddUserUseCase @Inject constructor(val repo: MadarRepository) {

    suspend operator fun invoke(user: User) = repo.addUser(user)
}