package com.karan.coingecko.demo.data.mapper

import android.app.usage.ConfigurationStats
import com.karan.coingecko.demo.data.network.response.CoinListResponse
import com.karan.coingecko.demo.data.network.response.CoinResponse
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsUIData
import com.karan.coingecko.demo.utils.addComma
import com.karan.coingecko.demo.utils.addPrefix
import com.karan.coingecko.demo.utils.round

class CoinMapper {

    fun map(from: CoinListResponse): TopCoinsUIData {
        return TopCoinsUIData(coinListDashboard = getCoinList(from))
    }

    private fun getCoinList(from: CoinListResponse): List<Coin> {
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
                price = it.data.priceData.price.round(2).addComma(),
                marketCap = it.data.priceData.marketCap.round(2),
                change24hr = it.data.priceData.change24hr.round(2),
                circulatingSupply = it.data.priceData.circulatingSupply.round(2),
                imageURL = it.coinInfoResponse.imageURL.addPrefix(Constants.BASE_URL_COIN_IMAGE),
                isPositiveChange = it.data.priceData.change24hr > 0
            )
        }

    }
}