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
import kotlin.reflect.KFunction0

@Composable
fun TopCoinsRoute(topCoinsViewModel: TopCoinsViewModel = hiltViewModel()) {
    val coinData by topCoinsViewModel.coinData.collectAsStateWithLifecycle()
    TopCoinsScreen(state = coinData)
}

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun TopCoinsScreen(
    state: TopCoinsUiState,
) {
    when (state) {
        is TopCoinsUiState.Success -> {
            Text(text = "Welcome to Dashboard ${(state as TopCoinsUiState.Success).feed.coinListDashboard}")
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

