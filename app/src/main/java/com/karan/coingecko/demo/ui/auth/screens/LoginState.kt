package com.karan.coingecko.demo.ui.auth.screens

sealed class LoginState {
    object LoggedIn : LoginState() // hasLoggedIn = true
    object NotLoggedIn : LoginState() // hasLoggedIn = false
    object Loading : LoginState() // Unknown
}