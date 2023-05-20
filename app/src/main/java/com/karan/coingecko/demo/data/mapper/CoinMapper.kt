package com.karan.coingecko.demo.data.mapper

import com.karan.coingecko.demo.data.network.response.CoinListResponse
import com.karan.coingecko.demo.data.network.response.CoinResponse
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.DashboardUIData

class CoinMapper {

    fun map(from: CoinListResponse): DashboardUIData {
        return DashboardUIData(coinListDashboard = getCoinList(from))
    }

    private fun getCoinList(from: CoinListResponse): List<Coin> {
        val listofCoins = mutableListOf<Coin>()
        from.coinList.forEach {
            listofCoins.add(Coin(id = it.coinInfoResponse.id, name = it.coinInfoResponse.name))
        }
        return listofCoins

    }
}