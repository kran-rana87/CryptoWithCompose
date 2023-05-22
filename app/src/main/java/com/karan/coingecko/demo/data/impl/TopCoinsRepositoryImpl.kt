package com.karan.coingecko.demo.data.impl

import com.karan.coingecko.demo.data.mapper.CoinMapper
import com.karan.coingecko.demo.data.network.NetworkService
import com.karan.coingecko.demo.domain.models.TopCoinsUIData
import com.karan.coingecko.demo.domain.repository.TopCoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopCoinsRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    TopCoinsRepository {

    override fun fetchTopCoins(): Flow<TopCoinsUIData> {
        return flow {
            emit(networkService.getDashboardData())
        }.map {
            CoinMapper().map(it)
        }
    }
}