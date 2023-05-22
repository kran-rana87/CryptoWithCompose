package com.karan.coingecko.demo.data.network

import com.karan.coingecko.demo.data.network.response.CoinListResponse
import retrofit2.http.GET

interface NetworkService {

    @GET("/data/top/mktcapfull?limit=30&tsym=AUD")
    suspend fun getDashboardData(): CoinListResponse
}

