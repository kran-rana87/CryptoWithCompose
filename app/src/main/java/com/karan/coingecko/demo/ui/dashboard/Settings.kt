package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions
import com.karan.coingecko.demo.ui.auth.AuthScreen


@Composable
fun settings(navigateToForgotPassword: () -> Unit) {
    Button(onClick = { navigateToForgotPassword() }) {
        "Take me to Forgot password"
    }
}