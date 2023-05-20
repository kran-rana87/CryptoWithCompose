package com.karan.coingecko.demo.ui.dashboard

import BottomTabBar
import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.karan.coingecko.demo.navigation.CoinGeckoDestinations
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions
import com.karan.coingecko.demo.ui.auth.AuthScreen
import com.karan.coingecko.demo.ui.auth.LoginScreen


sealed class DashboardScreen(val route: String) {
    object TopCoins : DashboardScreen("topCoins")
    object Favourites : DashboardScreen("favourites")
    object Settigns : DashboardScreen("settings")

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun dashboard(
    navHostController: NavHostController,
    navigationActions: CoinGeckoNavigationActions
) {
    Scaffold(bottomBar = { BottomTabBar(navHostController) }) {
        NavHost(
            navController = navHostController,
            route = CoinGeckoDestinations.DASHBOARD_ROUTE_GRAPH,
            startDestination = CoinGeckoDestinations.Top_COINS_GRAPH
        ) {

            topCoinGraph(navigationActions, navHostController)

            composable(AuthScreen.SignIn.route) {
                LoginScreen(
                    navigationActions.navigateTeSignUp,
                    navigationActions.navigateTeForgotPassword,
                    navigationActions.navigateToDashboard
                )
            }
            composable(DashboardScreen.Favourites.route) {
                favourites()
            }

            composable(DashboardScreen.Settigns.route) {
                SettingsScreen(navigationActions = navigationActions)
            }

        }
    }
}
