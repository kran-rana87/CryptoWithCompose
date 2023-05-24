package com.karan.coingecko.demo.ui.dashboard.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions
import com.karan.coingecko.demo.ui.dashboard.settings.screens.SettingsRoute

const val settings = "settings"

fun NavGraphBuilder.settingsGraph(
    navigationAction: CoinGeckoNavigationActions,
) {
    navigation(
        startDestination = settings,
        route = CoinGeckoGraphs.SETTINGS_GRAPH,
    ) {
        composable(settings) {
            SettingsRoute()
        }
    }
}