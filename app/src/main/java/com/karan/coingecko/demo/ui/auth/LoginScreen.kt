package com.karan.coingecko.demo.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.ui.MultiPreview
import com.karan.coingecko.demo.ui.dashboard.FavouritesScreen


@Composable
fun LoginRoutes(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(navigateToSignUp, navigateToForgotPassword, loginViewModel::login)
}

@Composable
fun LoginScreen(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    onLoginClick: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxHeight()) { padding ->
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Serif))

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                label = { Text(text = "Username") },
                value = "",
                onValueChange = { })

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                label = { Text(text = "Password") },
                value = "",
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { })

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { onLoginClick() },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                text = AnnotatedString("Forgot password?"),
                onClick = { navigateToForgotPassword() },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default
                )
            )
            Box(modifier = Modifier.fillMaxSize()) {
                ClickableText(
                    text = AnnotatedString("Sign up here"),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    onClick = { navigateToSignUp() },
                    style = TextStyle(
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline,
                    )
                )
            }
        }
    }
}

@MultiPreview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navigateToSignUp = { }, navigateToForgotPassword = { }, onLoginClick = {})
}
