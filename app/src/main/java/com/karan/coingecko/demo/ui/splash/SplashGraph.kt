package com.karan.coingecko.demo.ui.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoDestinations
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions

fun NavGraphBuilder.splashScreen(navigationAction: CoinGeckoNavigationActions) {
    navigation(
        startDestination = SplashScreen.Splash.route,
        route = CoinGeckoDestinations.SPLASH_ROUTE_GRAPH
    )
    {
        (composable(SplashScreen.Splash.route) {
            Splash {
                navigationAction.navigateTeLogin()
            }
        })
    }
}