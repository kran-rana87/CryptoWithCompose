package com.karan.coingecko.demo.ui.dashboard.topcoins.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.common.utils.Response
import com.karan.coingecko.demo.common.utils.removeComma
import com.karan.coingecko.demo.dispatchers.AppDispatchers
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.domain.repository.TopCoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TopCoinsViewModel @Inject constructor(
    private val topCoinRepository: TopCoinsRepository,
    appDispatchers: AppDispatchers,
) :
    ViewModel() {


    private val _currentSortingModeFlow = MutableStateFlow(SortingMode.MARKET_DESC)

    val coinData = coinData().combine(_currentSortingModeFlow) { data, sortingMethod ->
        when (data) {
            is Response.Success -> {
                val coinList = data.data?.coinListDashboard
                val sortedList = when (sortingMethod) {
                    SortingMode.MARKET_ASC -> sortList({ it.marketCap }, coinList, true)
                    SortingMode.MARKET_DESC -> sortList({ it.marketCap }, coinList, false)
                    SortingMode.NAME_ASC -> sortList({ it.name }, coinList, true)
                    SortingMode.NAME_DESC -> sortList({ it.name }, coinList, false)
                    SortingMode.PRICE_DESC -> sortList({ it.price.removeComma().toDouble() }, coinList, false)
                    SortingMode.PRICE_ASC -> sortList({ it.price.removeComma().toDouble() }, coinList, true)
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

    private fun <T, R : Comparable<R>> sortList(
        sortParam: (T) -> R?,
        inputList: List<T>?,
        ascending: Boolean
    ): List<T>? {
        val sortedList = inputList?.sortedBy(sortParam)
        return if (ascending) sortedList else sortedList?.reversed()
    }

    fun sort(type: SortingMode) {
        when (type) {
            SortingMode.NAME_ASC, SortingMode.NAME_DESC -> toggleSortingMode(
                SortingMode.NAME_ASC,
                SortingMode.NAME_DESC
            )
            SortingMode.PRICE_ASC, SortingMode.PRICE_DESC -> toggleSortingMode(
                SortingMode.PRICE_ASC,
                SortingMode.PRICE_DESC
            )
            else -> {}
        }
    }

    private fun toggleSortingMode(ascendingMode: SortingMode, descendingMode: SortingMode) {
        val currentMode = _currentSortingModeFlow.value
        val newMode = if (currentMode == ascendingMode) descendingMode else ascendingMode
        _currentSortingModeFlow.value = newMode
    }

    enum class SortingMode() {
        MARKET_ASC,
        MARKET_DESC,
        NAME_ASC,
        NAME_DESC,
        PRICE_ASC,
        PRICE_DESC
    }
}