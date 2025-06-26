package com.example.madarsoft.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Aziza Helmy on 26/06/2025.
 */
@Database(entities = [UserEntity::class], exportSchema = false, version = 1)
abstract class MadarDataBase: RoomDatabase() {
    abstract fun getMadarDao(): MadarDao
}