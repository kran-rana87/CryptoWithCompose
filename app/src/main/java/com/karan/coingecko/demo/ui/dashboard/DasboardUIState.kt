package com.karan.coingecko.demo.ui.dashboard

import com.karan.coingecko.demo.domain.models.DashboardUIData

sealed interface DashboardUIState {
    object Loading : DashboardUIState

    data class Success(
        val feed: DashboardUIData,
    ) : DashboardUIState

    object Failure : DashboardUIState

}
