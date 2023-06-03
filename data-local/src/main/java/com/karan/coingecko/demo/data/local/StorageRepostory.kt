package com.karan.coingecko.demo.data.local

import kotlinx.coroutines.flow.Flow

interface StorageRepostory {

    suspend fun updateLoginState(isLoggedIn: Boolean)
    suspend fun updateFavCoins(coinId: String)
    fun fetchFavCoins(): Flow<List<String>>

    fun fetchUserLoginState(): Flow<Boolean>
}