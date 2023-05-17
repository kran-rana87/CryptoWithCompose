package com.karan.coingecko.demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karan.coingecko.demo.ui.auth.authGraph
import com.karan.coingecko.demo.ui.dashboard.dashboard
import com.karan.coingecko.demo.ui.dashboard.dashboardGraph
import com.karan.coingecko.demo.ui.splash.splashScreen

@Composable
fun setupNavHost() {

    val navController = rememberNavController()
    val navigationAction = remember(navController) {
        CoinGeckoNavigationActions(navController)
    }
    NavHost(
        navController = navController,
        startDestination = CoinGeckoDestinations.SPLASH_ROUTE_GRAPH
    ) {
        splashScreen(navigationAction)
        authGraph(navigationAction)
        dashboardGraph(navigationAction)
    }
}

