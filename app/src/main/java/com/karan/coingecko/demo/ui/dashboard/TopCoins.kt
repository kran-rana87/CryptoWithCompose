package com.karan.coingecko.demo.ui.dashboard

import android.annotation.SuppressLint
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun topCoins(vm: DashboardViewModel = hiltViewModel()) {

    val coinData by vm.coinData.collectAsStateWithLifecycle()

    when (coinData) {
        is DashboardUIState.Success -> {
            Text(text = "Welcome to Dashboard ${(coinData as DashboardUIState.Success).feed.coinListDashboard}")
        }
        is DashboardUIState.Loading -> {
            CircularProgressIndicator()
        }
        else -> {
            Text(text = "Welcome to Dashboard")
        }
    }
}
