package com.karan.coingecko.demo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions
import com.karan.coingecko.demo.ui.auth.navigation.authScreenGraph
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.coingecko.demo.ui.dashboard.favorites.navigation.favourites
import com.karan.coingecko.demo.ui.dashboard.favorites.navigation.favouritesGraph
import com.karan.coingecko.demo.ui.dashboard.settings.navigation.settings
import com.karan.coingecko.demo.ui.dashboard.settings.navigation.settingsGraph
import com.karan.coingecko.demo.ui.dashboard.topcoins.navigation.topCoinGraph
import com.karan.coingecko.demo.ui.dashboard.topcoins.navigation.topCoins
import kotlinx.coroutines.flow.StateFlow


@Composable
fun CoinGeckoApp(userState: StateFlow<LoginState>) {

    val navController = rememberNavController()
    val navigationAction = remember(navController) {
        CoinGeckoNavigationActions(navController)
    }
    val loginState =
            userState.collectAsStateWithLifecycle(initialValue = LoginState.Loading)

    val initialRoute =
            if (loginState.value == LoginState.NotLoggedIn)
                CoinGeckoGraphs.AUTH_ROUTE_GRAPH
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
    Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            bottomBar = {
                if (loginState.value == LoginState.LoggedIn)
                    CoinGeckoBottomBar(navController)
            }) { padding ->
        Surface(
                Modifier
                        .fillMaxSize()
                        .padding(padding)
        ) {
            // Single NavHost for hosting all screens
            NavHost(
                    navController, startDestination = initialRoute
            ) {
                authScreenGraph(
                        navigationAction.navigateTeSignUp,
                        navigationAction.navigateTeForgotPassword
                )

                topCoinGraph(navigationAction)

                favouritesGraph(navigationAction)

                settingsGraph(navigationAction)
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
                        Text(navItem.title,
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

