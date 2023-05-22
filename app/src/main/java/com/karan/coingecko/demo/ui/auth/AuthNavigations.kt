package com.karan.coingecko.demo.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs



fun NavGraphBuilder.authScreenGraph(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
) {
    navigation(
        startDestination = AuthRoutes.SignIn.route,
        route = CoinGeckoGraphs.AUTH_ROUTE_GRAPH,
    ) {
        composable(AuthRoutes.SignIn.route) {
            LoginRoutes(navigateToSignUp, navigateToForgotPassword)
        }
        composable(AuthRoutes.SignUp.route) {
            SignupRoute()
        }
        composable(AuthRoutes.ForgotPassword.route) {
            ForgotPasswordRoute()
        }
    }
}