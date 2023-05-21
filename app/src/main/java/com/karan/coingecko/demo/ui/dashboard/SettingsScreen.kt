package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions


fun NavGraphBuilder.settingsGraph(
    navigationAction: CoinGeckoNavigationActions,
) {
    navigation(
        startDestination = TopLevelRoutes.Settings.route,
        route = CoinGeckoGraphs.SETTINGS_GRAPH,
    ) {
        composable(TopLevelRoutes.Settings.route) {
            SettingsScreen()
        }
    }
}

@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    Button(onClick = {
        settingsViewModel.logout()
    }) {
        Text(text = "Logout")
    }
}