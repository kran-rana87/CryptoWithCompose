package com.karan.coingecko.demo.ui.auth.screens.forgotpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.karan.coingecko.demo.ui.MultiPreview


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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        OutlinedTextField(
            label = { Text(text = "Email") },
            value = userNameInput.value,
            onValueChange = { userNameInput.value = it})

        Spacer(modifier = Modifier.height(20.dp))
        Surface(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }
}

@Composable
@MultiPreview
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}