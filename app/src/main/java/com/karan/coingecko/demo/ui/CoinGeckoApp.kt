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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs.TOP_COINS_GRAPH
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions
import com.karan.coingecko.demo.ui.auth.navigation.authScreenGraph
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.coingecko.demo.ui.dashboard.coinDetail.screens.CoinDetail
import com.karan.coingecko.demo.ui.dashboard.favorites.navigation.favourites
import com.karan.coingecko.demo.ui.dashboard.favorites.navigation.favouritesGraph
import com.karan.coingecko.demo.ui.dashboard.settings.navigation.settings
import com.karan.coingecko.demo.ui.dashboard.settings.navigation.settingsGraph
import com.karan.coingecko.demo.ui.dashboard.topcoins.navigation.*
import com.karan.coingecko.demo.ui.dashboard.topcoins.navigation.coinIdArgs


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
       topBar = {
           CoinGeckoAppBar()
       },
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

                topCoinGraph(
                    authState = authState,
                    navigateToLogin = navigationAction.navigateToLogin,
                    navigateToCoinDetails = navController::navigateToCoinDetails,
                            nestedGraphs = {
                                composable(
                                    route = coinDetail,
                                    arguments = listOf(
                                        navArgument(coinIdArgs) { type = NavType.StringType },
                                    )
                                ) {
                                    CoinDetail(
                                        it.arguments?.getString(coinIdArgs)
                                    )
                                }
                    },
                )

                favouritesGraph()

                settingsGraph(authState = authState) {
                    navigationAction.navigateToLogin()
                }
            }
        }
    }
}


private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: NavItem) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false



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
        topLevelDestinations.forEachIndexed { position,navItem ->
            val selected =  navBackStackEntry?.destination?.isTopLevelDestinationInHierarchy(navItem)
            BottomNavigationItem(
                alwaysShowLabel = false,
                selected = selected?:false,
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
                        tint = if (selected == true)
                            MaterialTheme.colors.secondaryVariant
                        else Color.Gray
                    )
                },
                label = {
                    Text(
                        navItem.title,
                        style = TextStyle(
                            color =
                            if (selected == true)
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

