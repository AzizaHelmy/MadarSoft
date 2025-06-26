package com.example.madarsoft.di

import android.content.Context
import com.example.madarsoft.data.source.local.MadarDataBase
import com.example.madarsoft.data.source.local.MadarRoomClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aziza Helmy on 26/06/2025.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideMadarDataBase(context: Context): MadarDataBase = MadarRoomClient.create(context)

    @Provides
    @Singleton
    fun provideMadarDao(madarDataBase: MadarDataBase) = madarDataBase.getMadarDao()

}