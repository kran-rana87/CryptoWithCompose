package com.karan.coingecko.demo.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.karan.coingecko.demo.ui.auth.authScreenGraph
import com.karan.coingecko.demo.ui.dashboard.Favourites
import com.karan.coingecko.demo.ui.dashboard.Settings
import com.karan.coingecko.demo.ui.dashboard.TopCoins
import com.karan.coingecko.demo.ui.dashboard.favouritesGraph
import com.karan.coingecko.demo.ui.dashboard.settingsGraph
import com.karan.coingecko.demo.ui.dashboard.topCoinGraph
import kotlinx.coroutines.flow.StateFlow

sealed class LoginState {
    object LoggedIn : LoginState() // hasLoggedIn = true
    object NotLoggedIn : LoginState() // hasLoggedIn = false
    object Loading : LoginState() // Unknown

}

@Composable
fun CoinGeckoApp(userState: StateFlow<LoginState>) {

    val navController = rememberNavController()
    val navigationAction = remember(navController) {
        CoinGeckoNavigationActions(navController)
    }
    val loginState =
        userState.collectAsStateWithLifecycle(initialValue = LoginState.Loading)

    val initialRoute =
        if (loginState.value == LoginState.NotLoggedIn) CoinGeckoGraphs.AUTH_ROUTE_GRAPH
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
        if (loginState.value == LoginState.LoggedIn) CoinGeckoAppBar(navController)
    }) { padding ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
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
fun CoinGeckoAppBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topLevelDestinations = listOf(
        NavItem(TopCoins.route, Icons.Default.Home, "Home"),
        NavItem(Favourites.route, Icons.Default.FavoriteBorder, "Favourites"),
        NavItem(Settings.route, Icons.Default.Settings, "Settings")
    )

    BottomNavigation(
        modifier = Modifier.clip(RoundedCornerShape(20.dp)).padding(5.dp),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.onBackground
    ) {
        topLevelDestinations.forEach { navItem ->
            BottomNavigationItem(
                alwaysShowLabel = false,
                selected = currentRoute == navItem.route,
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
                    Icon(navItem.icon, contentDescription = navItem.title)
                },
                label = {
                    Text(navItem.title)
                }
            )
        }
    }
}

data class NavItem(val route: String, val icon: ImageVector, val title: String)

