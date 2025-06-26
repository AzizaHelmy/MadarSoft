package com.example.madarsoft.di

import com.example.madarsoft.data.repo.MadarRepositoryImp
import com.example.madarsoft.domain.repo.MadarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aziza Helmy on 26/06/2025.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMadarRepository(madarRepositoryImp: MadarRepositoryImp): MadarRepository
}