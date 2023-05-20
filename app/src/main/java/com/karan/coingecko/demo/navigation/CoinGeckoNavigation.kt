package com.karan.coingecko.demo.navigation

import androidx.navigation.NavHostController
import com.karan.coingecko.demo.ui.auth.AuthScreen
import com.karan.coingecko.demo.ui.dashboard.DashboardScreen
import com.karan.coingecko.demo.ui.splash.SplashScreen

object CoinGeckoDestinations {
    const val AUTH_ROUTE_GRAPH = "login_route"
    const val DASHBOARD_ROUTE_GRAPH = "dashboard_route"
    const val Top_COINS_GRAPH = "top_coins_route"
}


class CoinGeckoNavigationActions(navController: NavHostController) {
    val navigateTeLogin: () -> Unit = {
        navController.navigate(AuthScreen.SignIn.route) {
            popUpTo(0)
        }
    }

    val navigateTeSignUp: () -> Unit = {
        navController.navigate(AuthScreen.SignUp.route) {
        }
    }

    val navigateTeForgotPassword: () -> Unit = {
        navController.navigate(AuthScreen.ForgotPassword.route) {
        }
    }

    val navigateToDashboard: () -> Unit = {
        navController.navigate(CoinGeckoDestinations.DASHBOARD_ROUTE_GRAPH) {
        }
    }
}