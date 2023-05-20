package com.karan.coingecko.demo.data.impl

import com.karan.coingecko.demo.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val storageRepo: StorageRepositoryImpl) :
    AuthRepository {

    //Fake Login
    override suspend fun login() {
        delay(2000)
        storageRepo.updateLoginState(true)
    }
}