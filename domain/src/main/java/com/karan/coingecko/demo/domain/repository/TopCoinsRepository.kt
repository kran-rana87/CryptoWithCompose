package com.karan.coingecko.demo.domain.repository

import com.karan.coingecko.demo.domain.models.TopCoinsUIData
import kotlinx.coroutines.flow.Flow

interface TopCoinsRepository {
    fun fetchTopCoins(): Flow<TopCoinsUIData>

}