import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.karan.coingecko.demo.ui.dashboard.DashboardScreen

@Composable
fun BottomTabBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        NavItem(DashboardScreen.TopCoins.route, Icons.Default.Home, "Home"),
        NavItem(DashboardScreen.Favourites.route, Icons.Default.Person, "Profile"),
        NavItem(DashboardScreen.Settigns.route, Icons.Default.Settings, "Settings")
    )

    BottomNavigation {
        bottomNavItems.forEach { navItem ->
            BottomNavigationItem(
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

