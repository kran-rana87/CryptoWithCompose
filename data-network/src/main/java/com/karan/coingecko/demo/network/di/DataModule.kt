package com.karan.coingecko.demo.network.di

import com.karan.coingecko.demo.network.repository.AuthRepository
import com.karan.coingecko.demo.network.repository.AuthRepositoryImpl
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
import com.karan.coingecko.demo.network.repository.TopCoinsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsDashboardRepository(dashboardRepo: TopCoinsRepositoryImpl): TopCoinsRepository

    @Binds
    fun bindsAuthRepository(authRepo: AuthRepositoryImpl): AuthRepository


}