package com.karan.coingecko.demo.ui.auth

sealed class AuthRoutes(val route: String) {
    object SignIn : AuthRoutes("signIn")
    object SignUp : AuthRoutes("signUp")
    object ForgotPassword : AuthRoutes("forgotPassword")
}