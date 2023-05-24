package com.karan.coingecko.demo.navigation

import androidx.navigation.NavHostController
import com.karan.coingecko.demo.ui.auth.navigation.forgotPassword
import com.karan.coingecko.demo.ui.auth.navigation.signUp

object CoinGeckoGraphs {
    const val AUTH_ROUTE_GRAPH = "login_graph"
    const val TOP_COINS_GRAPH = "top_coins_graph"
    const val SETTINGS_GRAPH = "settings_graph"
    const val FAVOURITES_GRAPH = "favourites_graph"
}

class CoinGeckoNavigationActions(navController: NavHostController) {

    val navigateTeSignUp: () -> Unit = {
        navController.navigate(signUp) {
        }
    }

    val navigateTeForgotPassword: () -> Unit = {
        navController.navigate(forgotPassword) {
        }
    }
}