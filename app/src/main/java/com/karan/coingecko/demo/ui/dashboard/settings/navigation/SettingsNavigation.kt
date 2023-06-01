package com.karan.coingecko.demo.ui.dashboard.settings.navigation

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.coingecko.demo.ui.dashboard.settings.screens.SettingsRoute

const val settings = "settings"

fun NavGraphBuilder.settingsGraph(
    authState: State<LoginState>,
    navigateToLogin: () -> Unit,
) {
    navigation(
        startDestination = settings,
        route = CoinGeckoGraphs.SETTINGS_GRAPH,
    ) {
        composable(settings) {
            SettingsRoute(
                authState = authState,
                navigateToLogin = navigateToLogin
            )
        }
    }
}