package com.example.madarsoft.domain.repo

import com.example.madarsoft.domain.enitiy.User

/**
 * Created by Aziza Helmy on 26/06/2025.
 */

interface MadarRepository {

    suspend fun addUser(user: User)

    suspend fun getUser(): User
}