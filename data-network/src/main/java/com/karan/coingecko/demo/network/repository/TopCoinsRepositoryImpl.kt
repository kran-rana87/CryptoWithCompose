package com.karan.coingecko.demo.network.repository

import com.karan.coingecko.demo.network.mapper.CoinMapper
import com.karan.coingecko.demo.domain.models.TopCoinsUIData
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
import com.karan.coingecko.demo.network.retrofit.NetworkService
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