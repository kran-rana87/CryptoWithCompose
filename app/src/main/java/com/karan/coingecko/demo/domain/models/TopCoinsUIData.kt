package com.karan.coingecko.demo.domain.models

data class TopCoinsUIData(val coinListDashboard: List<Coin>)

data class Coin(val id: Int, val name: String)