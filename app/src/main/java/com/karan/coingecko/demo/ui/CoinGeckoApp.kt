package com.karan.coingecko.demo.navigation

import BottomTabBar
import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karan.coingecko.demo.ui.MainViewModel
import com.karan.coingecko.demo.ui.auth.AuthRoutes
import com.karan.coingecko.demo.ui.auth.LoginScreen
import com.karan.coingecko.demo.ui.dashboard.favouritesGraph
import com.karan.coingecko.demo.ui.dashboard.settingsGraph
import com.karan.coingecko.demo.ui.dashboard.topCoinGraph

sealed class LoginState {
    object LoggedIn : LoginState() // hasLoggedIn = true
    object NotLoggedIn : LoginState() // hasLoggedIn = false
    object Loading : LoginState() // Unknown

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CoinGeckoApp(mainVIewModel: MainViewModel) {

    val navController = rememberNavController()
    val navigationAction = remember(navController) {
        CoinGeckoNavigationActions(navController)
    }
    val loginState =
        mainVIewModel.isUserLoggedIn.collectAsStateWithLifecycle(initialValue = LoginState.Loading)

    val initialRoute =
        if (loginState.value == LoginState.NotLoggedIn) AuthRoutes.SignIn.route
        else CoinGeckoGraphs.TOP_COINS_GRAPH

    Scaffold(bottomBar = {
        if (loginState.value == LoginState.LoggedIn) BottomTabBar(navController)
    }) {
        NavHost(
            navController, startDestination = initialRoute
        ) {
            composable(AuthRoutes.SignIn.route) {
                LoginScreen(
                    navigationAction.navigateTeSignUp,
                    navigationAction.navigateTeForgotPassword,
                )
            }

            topCoinGraph(navigationAction)

            favouritesGraph(navigationAction)

            settingsGraph(navigationAction)
        }
    }

}

