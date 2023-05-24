package com.karan.coingecko.demo.domain.usecases

//TODO Need to implement the useCases to avoid the dependency of Domain Models on Data layer.
/*
open class GetLoginStateUseCase @Inject constructor(private val storageRepository: StorageRepository) {

    operator fun invoke(): Flow<Boolean> {
        return flow {
            emit(storageRepository.fetchUserLoginState().collect())
        }
    }
}*/
