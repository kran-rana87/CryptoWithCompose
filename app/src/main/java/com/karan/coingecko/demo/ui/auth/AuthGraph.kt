package com.karan.coingecko.demo.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoDestinations
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions


fun NavGraphBuilder.authGraph(navigationAction: CoinGeckoNavigationActions) {
    navigation(
        startDestination = AuthScreen.SignIn.route,
        route = CoinGeckoDestinations.AUTH_ROUTE
    ) {
        composable(AuthScreen.SignIn.route) {
            LoginScreen(
                navigationAction.navigateTeSignUp,
                navigationAction.navigateTeForgotPassword,
                navigationAction.navigateToDashboard
            )
        }
        composable(AuthScreen.SignUp.route) { Signup() }
        composable(AuthScreen.ForgotPassword.route) { ForgotPassword() }

    }
}