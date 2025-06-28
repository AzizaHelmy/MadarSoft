package com.example.madarsoft.data.repo

import android.util.Log
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
            Log.e("TAG", "addUser: ${localDataSource.addUser(user.toEntity())}", )
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun getUser(): User {
        try {
            val user = localDataSource.getUser()
            return user?.toUser() ?: throw Exception("No user found")
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}