package com.karan.coingecko.demo.ui.dashboard.topcoins.navigation

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.coingecko.demo.ui.dashboard.topcoins.screens.TopCoinsRoute


const val topCoins = "topCoins"

fun NavGraphBuilder.topCoinGraph(
    authState: State<LoginState>,
    navigateToLogin: ()->Unit
) {
    navigation(
        startDestination = topCoins,
        route = CoinGeckoGraphs.TOP_COINS_GRAPH,
    ) {
        composable(topCoins) {
            TopCoinsRoute(
                authState = authState,
                navigateToLogin = navigateToLogin
            )
        }
    }
}

