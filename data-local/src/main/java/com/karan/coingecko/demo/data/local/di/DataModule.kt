package com.karan.coingecko.demo.data.local.di

import com.karan.coingecko.demo.data.local.StorageRepositoryImpl
import com.karan.coingecko.demo.data.local.StorageRepostory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsStorageRepo(authRepo: StorageRepositoryImpl): StorageRepostory


}