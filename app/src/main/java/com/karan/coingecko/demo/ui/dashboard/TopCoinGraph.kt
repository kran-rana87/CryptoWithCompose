package com.karan.coingecko.demo.ui.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karan.coingecko.demo.navigation.CoinGeckoGraphs
import com.karan.coingecko.demo.navigation.CoinGeckoNavigationActions

fun NavGraphBuilder.topCoinGraph(
    navigationAction: CoinGeckoNavigationActions,
) {
    navigation(
        startDestination = TopLevelRoutes.TopCoins.route,
        route = CoinGeckoGraphs.TOP_COINS_GRAPH,
    ) {
        composable(TopLevelRoutes.TopCoins.route) {
            TopCoinsScreen()
        }
    }
}


@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun TopCoinsScreen(
    vm: TopCoinsViewModel = hiltViewModel()
) {

    val coinData by vm.coinData.collectAsStateWithLifecycle()

    when (coinData) {
        is TopCoinsUiState.Success -> {
            Text(text = "Welcome to Dashboard ${(coinData as TopCoinsUiState.Success).feed.coinListDashboard}")
        }
        is TopCoinsUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {
            Text(text = "Welcome to Dashboard")
        }
    }
}

