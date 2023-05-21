package com.karan.coingecko.demo.di

import Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.karan.coingecko.demo.data.network.NetworkService
import com.karan.coingecko.demo.data.network.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        networkJson: Json,
    ): NetworkService {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(Constants.CRYPTO_BASE_URL)
            .addConverterFactory(
                networkJson.asConverterFactory(contentType),
            )
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideInterceptor() = RequestInterceptor()

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

}