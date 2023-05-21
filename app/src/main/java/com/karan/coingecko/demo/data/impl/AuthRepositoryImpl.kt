package com.karan.coingecko.demo.data.impl

import com.karan.coingecko.demo.data.local.StorageRepository
import com.karan.coingecko.demo.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val storageRepo: StorageRepository) :
    AuthRepository {


    override suspend fun login() {
        //Fake Login
        storageRepo.updateLoginState(true)
    }
}