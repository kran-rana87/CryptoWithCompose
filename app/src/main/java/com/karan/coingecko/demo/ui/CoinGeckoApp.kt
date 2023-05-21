package com.karan.coingecko.demo.navigation

import BottomTabBar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
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

    MainNavHost(
        navController = navController,
        loginState = loginState,
        navigationAction = navigationAction,
        initialRoute = initialRoute
    )
}

@Composable
fun MainNavHost(
    navController: NavHostController,
    loginState: State<LoginState>,
    navigationAction: CoinGeckoNavigationActions,
    initialRoute: String
) {
    Scaffold(bottomBar = {
        if (loginState.value == LoginState.LoggedIn) BottomTabBar(navController)
    }) { padding ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
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
}

