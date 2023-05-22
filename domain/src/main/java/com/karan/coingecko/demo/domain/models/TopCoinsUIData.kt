package com.karan.coingecko.demo.domain.models


data class TopCoinsUIData(val coinListDashboard: List<Coin>)

data class Coin(
    val id: Int,
    val name: String,
    val price: String,
    val change24hr: Double,
    val imageURL: String,
    val circulatingSupply: Double,
    val marketCap: Double,
    var isPositiveChange: Boolean = false,
)