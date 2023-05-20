package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions


@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    navigationActions: CoinGeckoNavigationActions
) {
    Button(onClick = {
        settingsViewModel.logout()
    }) {
        Text(text = "Logout")
    }
}