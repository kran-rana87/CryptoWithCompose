package com.karan.coingecko.demo.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karan.coingecko.demo.network.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthRepository) :
    ViewModel() {

    fun login() {
        viewModelScope.launch {
            authRepo.login()
        }
    }
}