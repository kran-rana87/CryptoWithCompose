package com.karan.coingecko.demo.domain.usecases

//TODO Need to implement the useCases to avoid the dependency of Domain Models on Data layer.
/*
class GetTopCoinsUseCase @Inject constructor(private val topCoinRepository: TopCoinsRepository) {*/

/*    operator fun invoke(): Flow<TopCoinsUIData> {
        return topCoinRepository.fetchTopCoins()
            .map {
                CoinMapper().map(it)
            }
    }*/

//}