package com.karan.coingecko.demo.ui.dashboard

sealed class TopLevelRoutes(val route: String) {
    object TopCoins : TopLevelRoutes("topCoins")
    object Favourites : TopLevelRoutes("favourites")
    object Settings : TopLevelRoutes("settings")
}