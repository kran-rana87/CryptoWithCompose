package com.karan.coingecko.demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.data.local.StorageRepostory
import com.karan.coingecko.demo.dispatchers.AppDispatchers
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val storageRepository: StorageRepostory,
    private val appDispatchers: AppDispatchers,
) : ViewModel() {


    val isUserLoggedIn = fetchUserState().map {
        when (it) {
            true -> LoginState.LoggedIn
            else -> LoginState.NotLoggedIn
        }
    }.flowOn(appDispatchers.io).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = LoginState.Loading
    )

    @VisibleForTesting
    internal fun fetchUserState() =
        storageRepository.fetchUserLoginState()
}
