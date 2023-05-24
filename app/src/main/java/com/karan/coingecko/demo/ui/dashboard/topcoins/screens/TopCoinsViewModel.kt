package com.karan.coingecko.demo.ui.dashboard.topcoins.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.common.utils.Resource
import com.karan.coingecko.demo.dispatchers.AppDispatchers
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val topCoinRepository: TopCoinsRepository,
    private val appDispatchers: AppDispatchers,
) :
    ViewModel() {
    val coinData: StateFlow<TopCoinsUiState> =
        coinData().map { data ->
                when (data) {
                    is Resource.Success ->
                        TopCoinsUiState.Success(TopCoinsData(data.data!!.coinListDashboard))
                    is Resource.Loading ->
                        TopCoinsUiState.Loading
                    else ->
                        TopCoinsUiState.Failure
                }
            }
            .flowOn(appDispatchers.io)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = TopCoinsUiState.Loading
            )

    private fun coinData(): Flow<Resource<TopCoinsData>> {
        return topCoinRepository.fetchTopCoins()
    }

    fun sortByName() {
        //TODO
    }


    private val MarketCapComparator = Comparator<Coin> { left, right ->
        right.marketCap.toInt().compareTo(left.marketCap.toInt())
    }


}