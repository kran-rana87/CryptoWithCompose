package com.karan.coingecko.demo.ui.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.karan.coingecko.demo.ui.MultiPreview


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
    val userNameInput = remember { mutableStateOf("Dummy Username") }
    val passwordInput = remember { mutableStateOf("Password") }

    Scaffold(modifier = Modifier.fillMaxHeight()) { padding ->
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Serif))

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                label = { Text(text = "Username") },
                value = userNameInput.value,
                onValueChange = { userNameInput.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                label = { Text(text = "Password") },
                value = passwordInput.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { passwordInput.value = it })

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
