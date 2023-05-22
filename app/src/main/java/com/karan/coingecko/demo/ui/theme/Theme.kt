package com.karan.coingecko.demo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple80,
    onPrimary = Purple20,
    secondary = Orange80,
    onSecondary = Orange20,
    background = Black
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    onPrimary = Color.DarkGray,
    secondary = DarkGreen40,
    onSecondary = Color.White,
    background = Color.White,
    surface = Color.White

)

@Composable
fun CoinGeckoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

