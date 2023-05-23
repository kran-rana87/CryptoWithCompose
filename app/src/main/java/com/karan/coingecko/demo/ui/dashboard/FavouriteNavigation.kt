package com.karan.coingecko.demo.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions

const val favourites = "favourites"

fun NavGraphBuilder.favouritesGraph(
    navigationAction: CoinGeckoNavigationActions,
) {
    navigation(
        startDestination = favourites,
        route = CoinGeckoGraphs.FAVOURITES_GRAPH,
    ) {
        composable(favourites) {
            FavouritesRoute()
        }
    }
}