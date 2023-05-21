package com.karan.coingecko.demo.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions


object Settings : TopLevelRoutes("settings")

fun NavGraphBuilder.settingsGraph(
    navigationAction: CoinGeckoNavigationActions,
) {
    navigation(
        startDestination = Settings.route,
        route = CoinGeckoGraphs.SETTINGS_GRAPH,
    ) {
        composable(Settings.route) {
            SettingsRoute()
        }
    }
}