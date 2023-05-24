package com.karan.coingecko.demo.network.repository

import com.karan.coingecko.demo.domain.models.TopCoinsData
import kotlinx.coroutines.flow.Flow

interface TopCoinsRepository {

    fun fetchTopCoins(): Flow<TopCoinsData>

}