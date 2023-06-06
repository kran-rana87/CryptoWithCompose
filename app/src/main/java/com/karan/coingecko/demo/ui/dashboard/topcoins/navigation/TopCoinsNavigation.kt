package com.karan.coingecko.demo.ui.dashboard.topcoins.navigation

import androidx.compose.runtime.State
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.coingecko.demo.ui.dashboard.coinDetail.screens.CoinDetail
import com.karan.coingecko.demo.ui.dashboard.topcoins.screens.TopCoinsRoute

internal const val coinIdArgs = "coinId"
const val topCoins = "topCoins"
const val coinDetail = "coin/{$coinIdArgs}"

fun NavGraphBuilder.topCoinGraph(
    authState: State<LoginState>,
    navigateToLogin: ()->Unit,
    navigateToCoinDetails :(String) ->Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        startDestination = topCoins,
        route = CoinGeckoGraphs.TOP_COINS_GRAPH,
    ) {
        composable(topCoins) {
            TopCoinsRoute(
                authState = authState,
                navigateToLogin = navigateToLogin,
                navigateToCoinDetail = navigateToCoinDetails
            )
        }
        nestedGraphs()
    }
}


fun NavController.navigateToCoinDetails(coinId: String) {
    this.navigate("coin/$coinId") {
        launchSingleTop = true
    }
}
