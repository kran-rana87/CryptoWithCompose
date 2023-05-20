package com.karan.coingecko.demo.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.data.impl.StorageRepositoryImpl
import com.karan.coingecko.demo.domain.models.DashboardUIData
import com.karan.coingecko.demo.domain.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepo: DashboardRepository,
    storageRepository: StorageRepositoryImpl
) :
    ViewModel() {
    val state = storageRepository.fetchUserState().flowOn(Dispatchers.IO).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = false
    )

    val coinData: StateFlow<DashboardUIState> =
        coinData()
            .map(DashboardUIState::Success)
            .flowOn(Dispatchers.IO)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DashboardUIState.Loading
            )

    private fun coinData(): Flow<DashboardUIData> {

        return dashboardRepo.getDashboardData()

    }
}