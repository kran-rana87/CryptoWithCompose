package com.karan.coingecko.demo.di

import com.karan.coingecko.demo.data.impl.AuthRepositoryImpl
import com.karan.coingecko.demo.data.impl.DashboardRepositoryImpl
import com.karan.coingecko.demo.domain.repository.AuthRepository
import com.karan.coingecko.demo.domain.repository.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindDashboardRepository(dashbaordRepo: DashboardRepositoryImpl): DashboardRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepo: AuthRepositoryImpl): AuthRepository


}