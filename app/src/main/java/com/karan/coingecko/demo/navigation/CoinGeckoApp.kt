package com.karan.coingecko.demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.karan.coingecko.demo.ui.auth.AuthScreen
import com.karan.coingecko.demo.ui.auth.ForgotPassword
import com.karan.coingecko.demo.ui.auth.LoginScreen
import com.karan.coingecko.demo.ui.auth.Signup
import com.karan.coingecko.demo.ui.auth.SplashScreen
import com.karan.coingecko.demo.ui.auth.authGraph
import com.karan.coingecko.demo.ui.dashboard.dashboardGraph

@Composable
fun setupNavHost() {

    val navController = rememberNavController()
    val navigationAction = remember(navController) {
        CoinGeckoNavigationActions(navController)
    }
    NavHost(navController = navController, startDestination = CoinGeckoDestinations.SPLASH_ROUTE) {
        splashScreen(navigationAction)
        authGraph(navigationAction)
        dashboardGraph(navigationAction)
    }

}


fun NavGraphBuilder.splashScreen(navigationAction: CoinGeckoNavigationActions) {
    navigation(startDestination = "splash", route = CoinGeckoDestinations.SPLASH_ROUTE)
    {
        (composable("splash") {
            SplashScreen {
                navigationAction.navigateTeLogin()
            }
        })
    }
}
