package com.example.madarsoft.data.source.local

import android.content.Context
import androidx.room.Room

/**
 * Created by Aziza Helmy on 26/06/2025.
 */


object MadarRoomClient {

    const val DATABASE_NAME = "Madar_dataBase"

    fun create(context: Context): MadarDataBase {
        return Room.databaseBuilder(context, MadarDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration(true)
            .build()
    }

}