package com.karan.coingecko.demo.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
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
import com.karan.flow.demo.R
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
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            if (loginState.value == LoginState.LoggedIn) CoinGeckoBottomBar(navController)
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
fun CoinGeckoBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topLevelDestinations = listOf(
        NavItem(TopCoins.route, Icons.Outlined.Home, "Explore"),
        NavItem(Favourites.route, Icons.Outlined.Star, "Favourites"),
        NavItem(Settings.route, Icons.Outlined.Menu, "More")
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
                        tint = if (selected) MaterialTheme.colors.secondaryVariant else Color.Gray
                    )
                },
                label = {
                    Text(
                        navItem.title,
                        style = TextStyle(
                            color =
                            if (selected) MaterialTheme.colors.secondaryVariant else Color.Gray
                        )
                    )
                }
            )
        }
    }
}

data class NavItem(val route: String, val icon: ImageVector, val title: String)

