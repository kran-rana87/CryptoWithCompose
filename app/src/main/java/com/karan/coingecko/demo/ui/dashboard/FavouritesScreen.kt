package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions


fun NavGraphBuilder.favouritesGraph(
    navigationAction: CoinGeckoNavigationActions,
) {
    navigation(
        startDestination = TopLevelRoutes.Favourites.route,
        route = CoinGeckoGraphs.FAVOURITES_GRAPH,
    ) {
        composable(TopLevelRoutes.Favourites.route) {
            FavouritesScreen()
        }
    }
}

@Composable
fun FavouritesScreen() {
    Text(text = "Welcome to Favourites")
}