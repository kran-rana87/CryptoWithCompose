package com.karan.coingecko.demo.data.network.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CoinListResponse(@SerialName("Data") val coinList: List<CoinResponse>)
@kotlinx.serialization.Serializable
data class CoinResponse(@SerialName("CoinInfo") val coinInfoResponse: CoinInfoResponse)
@kotlinx.serialization.Serializable
data class CoinInfoResponse(
    @SerialName("Id") val id: Int,
    @SerialName("Name") val name: String,
)