package com.karan.coingecko.demo.network

import app.cash.turbine.test
import com.karan.coingecko.demo.network.impl.TopCoinsRepositoryImpl
import com.karan.coingecko.demo.network.model.CoinInfoResponse
import com.karan.coingecko.demo.network.model.CoinListNetworkResponse
import com.karan.coingecko.demo.network.model.CoinResponse
import com.karan.coingecko.demo.network.model.Data
import com.karan.coingecko.demo.network.model.PriceData
import com.karan.coingecko.demo.network.retrofit.NetworkService
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TopCoinsRepositoryImplTest {
    private lateinit var topCoinRespository: TopCoinsRepositoryImpl

    @Mock
    private lateinit var networkService: NetworkService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        topCoinRespository = TopCoinsRepositoryImpl(networkService)

    }

    //TODO
    @Test
    fun `fetchTopCoins() returns Mapped Value when fetching Network Response`() = runTest {
        val mockCoinListResponse = CoinListNetworkResponse(
            listOf(
                CoinResponse(
                    CoinInfoResponse(1, "test-url", ""),
                    PriceData(Data(2.0, 3.4, 5.6, 6.4))
                )
            )
        )

        val mockFLow = flowOf<CoinListNetworkResponse>(mockCoinListResponse)
        Mockito.`when`(
            networkService.getDashboardData(
            )
        ).thenReturn(mockCoinListResponse)

        topCoinRespository.fetchTopCoins().test {
            cancelAndIgnoreRemainingEvents()
        }
    }
}