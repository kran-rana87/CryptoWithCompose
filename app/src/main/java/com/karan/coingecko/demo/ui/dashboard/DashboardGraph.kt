package com.karan.coingecko.demo.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoDestinations
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions

fun NavGraphBuilder.dashboardGraph(navigationAction: CoinGeckoNavigationActions) {
    navigation(
        startDestination = DashboardScreen.Main.route,
        route = CoinGeckoDestinations.DASHBOARD_ROUTE
    ) {
        composable(DashboardScreen.Main.route) {
            mainScreen(
            )
        }

    }
}