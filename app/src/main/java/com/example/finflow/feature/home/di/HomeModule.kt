package com.example.finflow.feature.home.di

import com.example.finflow.feature.home.data.repository.DashboardRepositoryImpl
import com.example.finflow.feature.home.domain.repository.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {


    @Binds
    abstract fun bindDashboardRepository(
        repositoryImpl: DashboardRepositoryImpl
    ): DashboardRepository
}