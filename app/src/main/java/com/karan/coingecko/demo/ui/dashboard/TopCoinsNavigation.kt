package com.karan.coingecko.demo.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions



object TopCoins : TopLevelRoutes("topCoins")

fun NavGraphBuilder.topCoinGraph(
    navigationAction: CoinGeckoNavigationActions
) {
    navigation(
        startDestination = TopCoins.route,
        route = CoinGeckoGraphs.TOP_COINS_GRAPH,
    ) {
        composable(TopCoins.route) {
            TopCoinsRoute()
        }
    }
}

