package com.karan.coingecko.demo.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoDestinations
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions

fun NavGraphBuilder.dashboardGraph(
    navigationAction: CoinGeckoNavigationActions
) {
/*    navigation(
        startDestination = DashboardScreen.Dashboard.route,
        route = CoinGeckoDestinations.DASHBOARD_ROUTE_GRAPH
    ) {
        composable(DashboardScreen.Dashboard.route) { dashboard(navigationActions = navigationAction) }
    }*/
}