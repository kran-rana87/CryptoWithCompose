package com.karan.coingecko.demo.ui.auth.navigation

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.coingecko.demo.ui.auth.screens.forgotpassword.ForgotPasswordRoute
import com.karan.coingecko.demo.ui.auth.screens.login.LoginRoutes
import com.karan.coingecko.demo.ui.auth.screens.signup.SignupRoute


const val signIn = "signIn"
const val signUp = "signUp"
const val forgotPassword = "forgotPassword"


fun NavGraphBuilder.authScreenGraph(
    authState: State<LoginState>,
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToDashboard: () -> Unit,
) {
    navigation(
        startDestination = signIn,
        route = CoinGeckoGraphs.AUTH_ROUTE_GRAPH,
    ) {
        composable(signIn) {
            LoginRoutes(
                navigateToSignUp,
                navigateToForgotPassword,
                authState = authState,
                navigateToDashboard = navigateToDashboard
            )
        }
        composable(signUp) {
            SignupRoute()
        }
        composable(forgotPassword) {
            ForgotPasswordRoute()
        }
    }
}