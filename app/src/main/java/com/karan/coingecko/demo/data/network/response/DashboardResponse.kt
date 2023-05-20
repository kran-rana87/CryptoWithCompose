package com.karan.coingecko.demo.data.network.response

import com.google.gson.annotations.SerializedName


data class CoinListResponse(@SerializedName("Data") val coinList: List<CoinResponse>)

data class CoinResponse(@SerializedName("CoinInfo") val coinInfoResponse: CoinInfoResponse)

data class CoinInfoResponse(
    @SerializedName("Id") val id: Int,
    @SerializedName("Name") val name: String,
)