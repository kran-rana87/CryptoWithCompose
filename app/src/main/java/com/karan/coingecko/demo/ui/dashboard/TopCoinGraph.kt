package com.karan.coingecko.demo.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoDestinations
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions

fun NavGraphBuilder.topCoinGraph(
    navigationAction: CoinGeckoNavigationActions,
    navHostController: NavHostController,
) {
    navigation(
        startDestination = DashboardScreen.TopCoins.route,
        route = CoinGeckoDestinations.Top_COINS_GRAPH,
    ) {
        composable(DashboardScreen.TopCoins.route) {
            topCoins(
                navigationActions = navigationAction
            )
        }
    }
}