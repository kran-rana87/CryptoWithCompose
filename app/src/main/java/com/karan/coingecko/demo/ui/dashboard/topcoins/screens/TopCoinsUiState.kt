package com.karan.coingecko.demo.ui.dashboard.topcoins.screens

import com.karan.coingecko.demo.domain.models.TopCoinsData

sealed interface TopCoinsUiState {
    object Loading : TopCoinsUiState

    data class Success(
        val feed: TopCoinsData,
    ) : TopCoinsUiState

    object Failure : TopCoinsUiState

}
