package com.karan.coingecko.demo.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.karan.flow.demo.R
import kotlinx.coroutines.delay


sealed class SplashScreen(val route: String) {
    object Splash : SplashScreen("splash")

}
@Composable
fun Splash(movetoLogin: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painterResource(id = R.drawable.ic_launcher_background), contentDescription = null,
        )
    }
    LaunchedEffect(key1 = true) {
        delay(2000)
        movetoLogin()
    }

}