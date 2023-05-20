package com.karan.coingecko.demo.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.karan.coingecko.demo.data.impl.StorageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext appContext: Context): StorageRepositoryImpl =
        StorageRepositoryImpl(appContext)
}