package com.karan.coingecko.demo.network.impl

import com.karan.coingecko.demo.data.local.StorageRepositoryImpl
import com.karan.coingecko.demo.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val storageRepo: StorageRepositoryImpl) :
    AuthRepository {


    override suspend fun login() {
        //Fake Login
        storageRepo.updateLoginState(true)
    }
}