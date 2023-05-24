package com.karan.coingecko.demo.network.di

import com.karan.coingecko.demo.network.impl.AuthRepositoryImpl
import com.karan.coingecko.demo.network.impl.TopCoinsRepositoryImpl
import com.karan.coingecko.demo.network.repository.AuthRepository
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
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