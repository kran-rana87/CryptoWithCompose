package com.karan.coingecko.demo.network.impl

import com.karan.coingecko.demo.common.utils.Resource
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.network.mapper.CoinMapper
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
import com.karan.coingecko.demo.network.retrofit.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//TODO Inject the mapper class as a Interface
class TopCoinsRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    TopCoinsRepository {

    override fun fetchTopCoins(): Flow<Resource<TopCoinsData>> = flow {
        try {
            emit(Resource.Loading())
            val coins = networkService.getDashboardData()
            emit(Resource.Success(CoinMapper().map(coins)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}