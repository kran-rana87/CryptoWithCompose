package com.karan.coingecko.demo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
        primary = Black,
        onPrimary = Color.White,
        secondary = Color.White,
        onSecondary = Color.White,
)

private val LightColorPalette = lightColors(
        primary = Color.White,
        onPrimary = Color.Black,
        secondary = Black,
        onSecondary = Color.White,
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

