package com.karan.coingecko.demo.domain.repository

import com.karan.coingecko.demo.common.utils.Response
import com.karan.coingecko.demo.domain.models.TopCoinsData
import kotlinx.coroutines.flow.Flow

interface TopCoinsRepository {

    fun fetchTopCoins(): Flow<Response<TopCoinsData>>

}