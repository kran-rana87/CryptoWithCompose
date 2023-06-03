package com.karan.coingecko.demo.ui.auth.screens.forgotpassword

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karan.coingecko.demo.ui.MultiPreview
import com.karan.coingecko.demo.ui.theme.CoinGeckoEditField
import com.karan.coingecko.demo.ui.theme.CoinGeckoRoundedCornerButton


@Composable
fun ForgotPasswordRoute() {
    ForgotPasswordScreen()
}

@Composable
private fun ForgotPasswordScreen() {
    val userNameInput = remember { mutableStateOf("Dummy Email") }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Forgot Password",
            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif)

        )
        Spacer(modifier = Modifier.height(100.dp))

        CoinGeckoEditField(
            state = userNameInput
        )

        Spacer(modifier = Modifier.height(20.dp))

        CoinGeckoRoundedCornerButton(title = "Submit") {
        }
    }
}

@Composable
@MultiPreview
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}
