package com.karan.coingecko.demo.network.impl

import com.karan.coingecko.demo.common.utils.Response
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.network.mapper.CoinMapper
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
import com.karan.coingecko.demo.network.retrofit.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//TODO Inject the mapper class as a Interface
class TopCoinsRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    TopCoinsRepository {

    override fun fetchTopCoins(): Flow<Response<TopCoinsData>> = flow {
        try {
            emit(Response.Loading())
            val coins = networkService.getDashboardData()
            emit(Response.Success(CoinMapper().map(coins)))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}