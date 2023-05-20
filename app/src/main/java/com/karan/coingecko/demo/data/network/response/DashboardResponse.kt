package com.karan.coingecko.demo.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinListResponse(@SerialName("Data") val coinList: List<CoinResponse>)

@Serializable
data class CoinResponse(@SerialName("CoinInfo") val coinInfoResponse: CoinInfoResponse)

@Serializable
data class CoinInfoResponse(
    @SerialName("Id") val id: Int,
    @SerialName("Name") val name: String,
)