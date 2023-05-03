package com.karan.coingecko.demo.navigation

import androidx.navigation.NavHostController
import com.karan.coingecko.demo.ui.auth.AuthScreen
import com.karan.coingecko.demo.ui.dashboard.DashboardScreen

object CoinGeckoDestinations {
    const val AUTH_ROUTE = "login_route"
    const val SPLASH_ROUTE = "splash_route"
    const val DASHBOARD_ROUTE = "dashboard_route"
}


class CoinGeckoNavigationActions(navController: NavHostController) {
    val navigateTeLogin: () -> Unit = {
        navController.navigate(AuthScreen.SignIn.route) {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }

    val navigateTeSignUp: () -> Unit = {
        navController.navigate(AuthScreen.SignUp.route) {
        }
    }

    val navigateTeForgotPassword: () -> Unit = {
        navController.navigate(AuthScreen.ForgotPassword.route) {
        }
    }

    val navigateToDashboard: () -> Unit = {
        navController.navigate(DashboardScreen.Main.route) {
            //Clear Backstack before moving to Dashboard
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }
}