package com.karan.coingecko.demo.domain.repository

import com.karan.coingecko.demo.domain.models.DashboardResponse
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    fun getDashboardData(): Flow<DashboardResponse>

}