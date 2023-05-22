package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.data.local.StorageRepository
import com.karan.coingecko.demo.domain.models.TopCoinsUIData
import com.karan.coingecko.demo.domain.repository.TopCoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TopCoinsViewModel @Inject constructor(
    private val dashboardRepo: TopCoinsRepository
) :
    ViewModel() {

    val coinData: StateFlow<TopCoinsUiState> =
        coinData()
            .map(TopCoinsUiState::Success)
            .flowOn(Dispatchers.IO)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = TopCoinsUiState.Loading
            )

    private fun coinData(): Flow<TopCoinsUIData> {
        return dashboardRepo.fetchTopCoins()
    }


}