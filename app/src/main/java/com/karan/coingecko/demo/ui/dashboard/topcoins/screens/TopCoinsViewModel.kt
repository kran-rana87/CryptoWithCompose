package com.karan.coingecko.demo.ui.dashboard.topcoins.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.common.utils.Response
import com.karan.coingecko.demo.dispatchers.AppDispatchers
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TopCoinsViewModel @Inject constructor(
    private val topCoinRepository: TopCoinsRepository,
    appDispatchers: AppDispatchers,
) :
    ViewModel() {
    val coinData: StateFlow<TopCoinsUiState> =
        coinData().map { data ->
            when (data) {
                is Response.Success ->
                    TopCoinsUiState.Success(TopCoinsData(data.data!!.coinListDashboard))
                is Response.Loading ->
                    TopCoinsUiState.Loading
                else ->
                    TopCoinsUiState.Failure
            }
        }
            .flowOn(appDispatchers.io)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = TopCoinsUiState.Loading
            )

    private fun coinData(): Flow<Response<TopCoinsData>> {
        return topCoinRepository.fetchTopCoins()
    }

    fun sortByName() {
        //TODO
    }

    //TODO
    private val marketCapComparator = Comparator<Coin> { left, right ->
        right.marketCap.toInt().compareTo(left.marketCap.toInt())
    }

}