package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable


sealed class DashboardScreen(val route: String) {
    object Main : DashboardScreen("landing")
}

@Composable
fun mainScreen() {
    Text(text = "Welcome to Dashboard")
}