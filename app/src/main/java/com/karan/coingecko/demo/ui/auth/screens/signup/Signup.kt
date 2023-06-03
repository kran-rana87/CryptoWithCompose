package com.karan.coingecko.demo.ui.auth.screens.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karan.coingecko.demo.ui.MultiPreview
import com.karan.coingecko.demo.ui.theme.CoinGeckoEditField
import com.karan.coingecko.demo.ui.theme.CoinGeckoRoundedCornerButton

@Composable
fun SignupRoute() {
    Signup()
}

@Composable
private fun Signup() {
    val userNameInput = remember { mutableStateOf("Dummy Username") }
    val passwordInput = remember { mutableStateOf("Password") }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Signup", style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        CoinGeckoEditField(state = userNameInput)

        Spacer(modifier = Modifier.height(20.dp))

        CoinGeckoEditField(
            state = passwordInput,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Surface(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            CoinGeckoRoundedCornerButton(title = "Signup") {
            }
        }
    }
}


@Composable
@MultiPreview
fun SignupScreenPreview() {
    Signup()
}