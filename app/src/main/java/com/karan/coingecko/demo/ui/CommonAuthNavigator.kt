package com.karan.coingecko.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import com.karan.coingecko.demo.ui.auth.screens.LoginState

@Composable
fun AuthenticatedContentOrLogin(
    authState: State<LoginState>,
    navigateToLogin: () -> Unit,
    content: @Composable () -> Unit
) {
    when (authState.value) {
        LoginState.LoggedIn -> content()
        else -> {
            LaunchedEffect(authState) {
                navigateToLogin()
            }
        }
    }
}

@Composable
fun AuthenticateOrDisplayLogin(
    authState: State<LoginState>,
    loginSuccess: () -> Unit,
    content: @Composable () -> Unit
) {
    when (authState.value) {
        LoginState.LoggedIn ->
            LaunchedEffect(authState) {
                loginSuccess()
            }
        else -> content()
    }
}

