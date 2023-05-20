package com.karan.coingecko.demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.karan.coingecko.demo.data.impl.StorageRepositoryImpl
import com.karan.coingecko.demo.ui.auth.LoginScreen
import com.karan.coingecko.demo.ui.dashboard.dashboard

sealed class LoginState {
    object LoggedIn : LoginState() // hasLoggedIn = true
    object NotLoggedIn : LoginState() // hasLoggedIn = false
}

@Composable
fun CoinGeckoApp(storageRepository: StorageRepositoryImpl) {

    val navController = rememberNavController()
    val navigationAction = remember(navController) {
        CoinGeckoNavigationActions(navController)
    }
    val state = storageRepository.fetchUserState().collectAsStateWithLifecycle(initialValue = false)

    if (state.value) {
        dashboard(navigationActions = navigationAction, navHostController = navController)
    } else {
        LoginScreen(
            navigationAction.navigateTeSignUp,
            navigationAction.navigateTeForgotPassword,
            navigationAction.navigateToDashboard
        )
    }

}

