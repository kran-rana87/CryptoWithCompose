package com.karan.coingecko.demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.data.local.StorageRepository
import com.karan.coingecko.demo.navigation.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(storageRepository: StorageRepository) : ViewModel() {

    val isUserLoggedIn = storageRepository.fetchUserLoginState().map {
        when (it) {
            true -> LoginState.LoggedIn
            else -> LoginState.NotLoggedIn
        }
    }.flowOn(Dispatchers.IO).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = LoginState.Loading
    )
}