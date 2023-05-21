package com.karan.coingecko.demo.ui.dashboard

import com.karan.coingecko.demo.domain.models.TopCoinsUIData

sealed interface TopCoinsUiState {
    object Loading : TopCoinsUiState

    data class Success(
        val feed: TopCoinsUIData,
    ) : TopCoinsUiState

    object Failure : TopCoinsUiState

}
