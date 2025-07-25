package com.example.madarsoft.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Aziza Helmy on 26/06/2025.
 */

@Dao
interface MadarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 1")
    suspend fun getUser(): UserEntity?


}