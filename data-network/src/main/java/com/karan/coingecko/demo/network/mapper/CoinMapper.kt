package com.karan.coingecko.demo.network.mapper

import com.karan.coingecko.demo.common.utils.Constants
import com.karan.coingecko.demo.common.utils.addComma
import com.karan.coingecko.demo.common.utils.addPrefix
import com.karan.coingecko.demo.common.utils.round
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.network.model.CoinListNetworkResponse
import com.karan.coingecko.demo.network.model.CoinResponse

class CoinMapper {

    fun map(from: CoinListNetworkResponse): TopCoinsData {
        return TopCoinsData(coinListDashboard = getCoinList(from))
    }

    private fun getCoinList(from: CoinListNetworkResponse): List<Coin> {
        val listofCoins = mutableListOf<Coin>()
        from.coinList.forEach {
            listofCoins.add(getCoinData(it))
        }
        return listofCoins
    }

    private fun getCoinData(coinResponse: CoinResponse): Coin {
        coinResponse.let {
            return Coin(
                id = it.coinInfoResponse.id,
                name = it.coinInfoResponse.name,
                price = it.data?.priceData?.price?.round(2)?.addComma() ?: "0",
                marketCap = it.data?.priceData?.marketCap?.round(2) ?: 0.0,
                change24hr = it.data?.priceData?.change24hr?.round(2) ?: 0.0,
                circulatingSupply = it.data?.priceData?.circulatingSupply?.round(2) ?: 0.0,
                imageURL = it.coinInfoResponse.imageURL.addPrefix(Constants.BASE_URL_COIN_IMAGE),
                isPositiveChange = it.data?.priceData?.change24hr?.let
                { change24hr -> change24hr > 0 } == true
            )
        }

    }
}