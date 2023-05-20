package com.karan.coingecko.demo.domain.repository

import com.karan.coingecko.demo.data.network.response.CoinResponse
import com.karan.coingecko.demo.domain.models.DashboardUIData
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun getDashboardData(): Flow<DashboardUIData>

}