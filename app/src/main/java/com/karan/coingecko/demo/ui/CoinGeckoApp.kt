package com.karan.coingecko.demo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs.TOP_COINS_GRAPH
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions
import com.karan.coingecko.demo.ui.auth.navigation.authScreenGraph
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.coingecko.demo.ui.dashboard.favorites.navigation.favourites
import com.karan.coingecko.demo.ui.dashboard.favorites.navigation.favouritesGraph
import com.karan.coingecko.demo.ui.dashboard.settings.navigation.settings
import com.karan.coingecko.demo.ui.dashboard.settings.navigation.settingsGraph
import com.karan.coingecko.demo.ui.dashboard.topcoins.navigation.topCoinGraph
import com.karan.coingecko.demo.ui.dashboard.topcoins.navigation.topCoins


@Composable
fun CoinGeckoApp(
    mainViewModel: MainViewModel,
) {
    val navController = rememberNavController()
    val navigationAction = remember(navController) {
        CoinGeckoNavigationActions(navController)
    }
    val loginState =
        mainViewModel.isUserLoggedIn.collectAsStateWithLifecycle(initialValue = LoginState.Loading)

    MainNavHost(
        navController = navController,
        authState = loginState,
        navigationAction = navigationAction,
    )
}

@Composable
fun MainNavHost(
    navController: NavHostController,
    authState: State<LoginState>,
    navigationAction: CoinGeckoNavigationActions,
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            if (authState.value == LoginState.LoggedIn)
                CoinGeckoBottomBar(navController)
        }) { padding ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Single NavHost for hosting all screens with Top Coins as the start Destination
            NavHost(
                navController, startDestination = TOP_COINS_GRAPH
            ) {
                authScreenGraph(
                    authState = authState,
                    navigateToSignUp = navigationAction.navigateTeSignUp,
                    navigateToForgotPassword = navigationAction.navigateTeForgotPassword,
                    navigateToDashboard = navigationAction.navigateToDashboard
                )

                topCoinGraph(authState = authState) {
                    navigationAction.navigateToLogin()
                }

                favouritesGraph()

                settingsGraph(authState = authState) {
                    navigationAction.navigateToLogin()
                }
            }
        }
    }
}

@Composable
fun CoinGeckoBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topLevelDestinations = listOf(
        NavItem(topCoins, Icons.Outlined.Home, "Explore"),
        NavItem(favourites, Icons.Outlined.Star, "Favourites"),
        NavItem(settings, Icons.Outlined.Menu, "More")
    )

    BottomNavigation(

        modifier = Modifier
            .clip(CircleShape)
            .padding(10.dp),
        backgroundColor = MaterialTheme.colors.primarySurface
    ) {
        topLevelDestinations.forEach { navItem ->
            val selected = currentRoute == navItem.route
            BottomNavigationItem(
                alwaysShowLabel = false,
                selected = selected,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        navItem.icon,
                        contentDescription = navItem.title,
                        tint = if (selected)
                            MaterialTheme.colors.secondaryVariant
                        else Color.Gray
                    )
                },
                label = {
                    Text(
                        navItem.title,
                        style = TextStyle(
                            color =
                            if (selected)
                                MaterialTheme.colors.secondaryVariant
                            else Color.Gray
                        )
                    )
                }
            )
        }
    }
}

data class NavItem(val route: String, val icon: ImageVector, val title: String)

