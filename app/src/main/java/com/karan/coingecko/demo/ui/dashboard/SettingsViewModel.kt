package com.karan.coingecko.demo.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.data.local.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SettingsViewModel @Inject constructor(val storageRepository: StorageRepository) :
    ViewModel() {

    fun logout() {
        viewModelScope.launch {
            storageRepository.updateLoginState(false)
        }
    }
}