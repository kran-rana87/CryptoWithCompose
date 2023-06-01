package com.karan.coingecko.demo.ui.auth.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.karan.coingecko.demo.ui.AuthenticateOrDisplayLogin
import com.karan.coingecko.demo.ui.MultiPreview
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.coingecko.demo.ui.theme.CoinGeckoEditField
import com.karan.coingecko.demo.ui.theme.CoinGeckoRoundedCornerButton
import com.karan.flow.demo.R


@Composable
fun LoginRoutes(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToDashboard: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
    authState: State<LoginState>
) {
    AuthenticateOrDisplayLogin(
        authState = authState,
        loginSuccess = navigateToDashboard
    ) {
        LoginScreen(
            navigateToSignUp,
            navigateToForgotPassword,
            loginViewModel::login
        )
    }
}

@Composable
fun LoginScreen(
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    onLoginClick: () -> Unit
) {
    var userNameInput by rememberSaveable { mutableStateOf("username") }
    var passwordInput by rememberSaveable { mutableStateOf("password") }

    Scaffold(modifier = Modifier.fillMaxHeight()) { padding ->
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painterResource(id = R.mipmap.ic_launcher), "",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            CoinGeckoEditField(text = userNameInput) {
                userNameInput = it
            }


            Spacer(modifier = Modifier.height(20.dp))
            CoinGeckoEditField(
                text = passwordInput,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            ) {
                passwordInput = it
            }

            Spacer(modifier = Modifier.height(20.dp))

            CoinGeckoRoundedCornerButton("Login") {
                onLoginClick()
            }

            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                text = AnnotatedString("Forgot password?"),
                onClick = { navigateToForgotPassword() },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    color = MaterialTheme.colors.onBackground
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
                        color = MaterialTheme.colors.onBackground
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
