package com.karan.coingecko.demo.ui.dashboard

import BottomTabBar
import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karan.coingecko.demo.navigation.CoinGeckoDestinations
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions


sealed class DashboardScreen(val route: String) {
    object Dashboard : DashboardScreen("dashboard")
    object TopCoins : DashboardScreen("topCoins")
    object Favourites : DashboardScreen("favourites")
    object Settigns : DashboardScreen("settings")

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun dashboard(
    navHostController: NavHostController = rememberNavController(),
    navigationActions: CoinGeckoNavigationActions
) {
    DashboardNavigationActions(navHostController)
    Scaffold(bottomBar = { BottomTabBar(navHostController) }) {
        NavHost(
            navController = navHostController,
            route = CoinGeckoDestinations.DASHBOARD_ROUTE_GRAPH,
            startDestination = DashboardScreen.TopCoins.route
        ) {
            composable(DashboardScreen.TopCoins.route) { topCoins() }
            composable(DashboardScreen.Favourites.route) { favourites() }
            composable(DashboardScreen.Settigns.route) {
                settings(navigationActions.navigateTeForgotPassword)
            }

        }
    }
}
