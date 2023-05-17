package com.karan.coingecko.demo.data.impl

import com.karan.coingecko.demo.data.mapper.CoinMapper
import com.karan.coingecko.demo.data.network.NetworkService
import com.karan.coingecko.demo.data.network.response.CoinResponse
import com.karan.coingecko.demo.domain.models.DashboardUIData
import com.karan.coingecko.demo.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    DashboardRepository {

    override fun getDashboardData(): Flow<DashboardUIData> {
        return flow {
            emit(networkService.getDashboardData())
        }.map { CoinMapper().map(it) }
    }
}