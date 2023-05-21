package com.karan.coingecko.demo.data.network

import com.karan.coingecko.demo.data.network.response.CoinListResponse
import retrofit2.http.GET

interface NetworkService {

    @GET("/data/top/totalvolfull?limit=10&tsym=AUD")
    suspend fun getDashboardData(): CoinListResponse
}

