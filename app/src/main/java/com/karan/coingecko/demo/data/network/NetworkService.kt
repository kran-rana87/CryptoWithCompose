package com.karan.coingecko.demo.data.network

import com.karan.coingecko.demo.domain.models.DashboardResponse
import dagger.Module
import retrofit2.http.GET

interface NetworkService {

    @GET("/data/top/totalvolfull?limit=10&tsym=AUD")
    suspend fun getDashboardData(): DashboardResponse

}