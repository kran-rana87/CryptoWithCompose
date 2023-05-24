package com.karan.coingecko.demo.ui.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.ui.auth.SignupRoute
import com.karan.coingecko.demo.ui.auth.screens.forgotpassword.ForgotPasswordRoute
import com.karan.coingecko.demo.ui.auth.screens.login.LoginRoutes


const val signIn = "signIn"
const val signUp = "signUp"
const val forgotPassword = "forgotPassword"


fun NavGraphBuilder.authScreenGraph(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
) {
    navigation(
        startDestination = signIn,
        route = CoinGeckoGraphs.AUTH_ROUTE_GRAPH,
    ) {
        composable(signIn) {
            LoginRoutes(navigateToSignUp, navigateToForgotPassword)
        }
        composable(signUp) {
            SignupRoute()
        }
        composable(forgotPassword) {
            ForgotPasswordRoute()
        }
    }
}