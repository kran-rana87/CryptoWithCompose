package com.karan.coingecko.demo.ui.dashboard.topcoins.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.common.utils.Response
import com.karan.coingecko.demo.dispatchers.AppDispatchers
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopCoinsViewModel @Inject constructor(
    private val topCoinRepository: TopCoinsRepository,
    appDispatchers: AppDispatchers,
) :
    ViewModel() {


    private val _currentSortingModeFlow: MutableStateFlow<SortingMode> = MutableStateFlow(SortingMode.MARKET)
    val currentSortingModeFlow = _currentSortingModeFlow.asStateFlow()


    val coinData = coinData().combine(currentSortingModeFlow) { data, sortingMethod ->
        when (data) {
            is Response.Success -> {
               val sortedList = when (sortingMethod) {
                    SortingMode.MARKET -> data.data?.coinListDashboard?.sortedBy { coin -> coin.marketCap.toInt() }
                    SortingMode.NAME -> data.data?.coinListDashboard?.sortedBy { coin -> coin.name }
                }
                TopCoinsUiState.Success(TopCoinsData(sortedList!!))
            }
            is Response.Loading ->
                TopCoinsUiState.Loading
            else ->
                TopCoinsUiState.Failure
        }
    }.flowOn(appDispatchers.io)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = TopCoinsUiState.Loading
        )


    private fun coinData(): Flow<Response<TopCoinsData>> {
        return topCoinRepository.fetchTopCoins()
    }

    fun sortByName() {
        _currentSortingModeFlow.value = SortingMode.NAME
    }

    enum class SortingMode {
        MARKET,
        NAME
    }
}