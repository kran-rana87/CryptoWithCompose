package com.karan.coingecko.demo.data.impl

import com.karan.coingecko.demo.data.network.NetworkService
import com.karan.coingecko.demo.domain.models.DashboardResponse
import com.karan.coingecko.demo.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DashboardRepositoryImpl@Inject constructor(private val networkService: NetworkService) :
    DashboardRepository {

    override fun getDashboardData(): Flow<DashboardResponse> {
       return  flow<DashboardResponse>{
            networkService.getDashboardData()
        }.map {  it}
    }
}