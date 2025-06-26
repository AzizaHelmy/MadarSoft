package com.example.madarsoft.data.repo

import com.example.madarsoft.data.mapper.toEntity
import com.example.madarsoft.data.mapper.toUser
import com.example.madarsoft.data.source.local.MadarDao
import com.example.madarsoft.domain.enitiy.User
import com.example.madarsoft.domain.repo.MadarRepository
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 26/06/2025.
 */

class MadarRepositoryImp @Inject constructor(val localDataSource: MadarDao) : MadarRepository {

    override suspend fun addUser(user: User) {
        try {
            localDataSource.addUser(user.toEntity())
        } catch (e: Exception) {
            throw Exception("Failed to add user")
        }
    }

    override suspend fun getUser(): User {
        try {
            return localDataSource.getUser().toUser()
        } catch (e: Exception) {
            throw Exception("Failed to get user")
        }
    }
}