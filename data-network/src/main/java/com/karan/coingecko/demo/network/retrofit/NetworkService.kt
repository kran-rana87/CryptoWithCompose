package com.karan.coingecko.demo.network.retrofit

import com.karan.coingecko.demo.network.model.CoinListNetworkResponse
import retrofit2.http.GET

interface NetworkService {

    @GET("/data/top/mktcapfull?limit=30&tsym=AUD")
    suspend fun getDashboardData(): CoinListNetworkResponse
}

