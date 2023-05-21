package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun FavouritesRoute() {
    FavouritesScreen()
}

@Composable
internal fun FavouritesScreen() {
    Text(text = "Welcome to Favourites")
}