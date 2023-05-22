package com.karan.coingecko.demo.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinListResponse(@SerialName("Data") val coinList: List<CoinResponse>)

@Serializable
data class CoinResponse(
    @SerialName("CoinInfo") val coinInfoResponse: CoinInfoResponse,
    @SerialName("RAW") val data: PriceData

)

@Serializable
data class CoinInfoResponse(
    @SerialName("Id") val id: Int,
    @SerialName("Name") val name: String,
    @SerialName("ImageUrl") val imageURL: String,
)

@Serializable
data class PriceData(
    @SerialName("AUD") val priceData: Data
)

@Serializable
data class Data(
    @SerialName("CHANGEPCT24HOUR")val change24hr: Double,
    @SerialName("CIRCULATINGSUPPLY")val circulatingSupply: Double,
    @SerialName("MKTCAP")val marketCap: Double,
    @SerialName("PRICE")val price: Double,
)
