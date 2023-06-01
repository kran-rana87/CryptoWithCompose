package com.karan.coingecko.demo.ui.dashboard.favorites.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.karan.coingecko.demo.ui.CoinGeckoAppBar
import com.karan.coingecko.demo.ui.MultiPreview


@Composable
internal fun FavouritesRoute() {
    FavouritesScreen()
}

@Composable
internal fun FavouritesScreen() {
    Scaffold(topBar = {
        CoinGeckoAppBar()
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(60.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = "Favourite Page",
                tint = Color.LightGray
            )
        }
    }
}

@MultiPreview
@Composable
fun FavouritesPreview() {
    FavouritesScreen()
}