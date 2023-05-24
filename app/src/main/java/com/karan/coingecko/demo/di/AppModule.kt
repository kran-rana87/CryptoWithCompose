package com.karan.coingecko.demo.di

import com.karan.coingecko.demo.dispatchers.AppDispatchers
import com.karan.coingecko.demo.dispatchers.AppDispatchersImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDispatcher(): AppDispatchers = AppDispatchersImp()
}