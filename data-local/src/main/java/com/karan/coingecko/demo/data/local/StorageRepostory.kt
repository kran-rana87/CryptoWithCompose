package com.karan.coingecko.demo.data.local

import kotlinx.coroutines.flow.Flow

interface StorageRepostory {

    suspend fun updateLoginState(isLoggedIn: Boolean)

    fun fetchUserLoginState(): Flow<Boolean>
}