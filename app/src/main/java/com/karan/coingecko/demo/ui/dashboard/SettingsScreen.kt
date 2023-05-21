package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import kotlin.reflect.KFunction0


@Composable
fun SettingsRoute(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    SettingsScreen(onLogoutClick = settingsViewModel::logout)
}

@Composable
fun SettingsScreen(onLogoutClick: () -> Unit) {
    Button(onClick = {
        onLogoutClick()
    }) {
        Text(text = "Logout")
    }
}