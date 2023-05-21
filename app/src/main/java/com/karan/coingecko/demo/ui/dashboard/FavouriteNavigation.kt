package com.karan.coingecko.demo.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions

object Favourites : TopLevelRoutes("favourites")

fun NavGraphBuilder.favouritesGraph(
    navigationAction: CoinGeckoNavigationActions,
) {
    navigation(
        startDestination = Favourites.route,
        route = CoinGeckoGraphs.FAVOURITES_GRAPH,
    ) {
        composable(Favourites.route) {
            FavouritesRoute()
        }
    }
}