package com.karan.flow.demo

import app.cash.turbine.test
import com.karan.coingecko.demo.common.utils.Resource
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.network.repository.TopCoinsRepository
import com.karan.coingecko.demo.ui.dashboard.topcoins.screens.TopCoinsUiState
import com.karan.coingecko.demo.ui.dashboard.topcoins.screens.TopCoinsViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class TopCoinViewModelTest {
    private lateinit var viewModel: TopCoinsViewModel

    lateinit var topCoinsRepository: MockToCoinRepo

    private lateinit var appDispatcher: MockCoroutineDispatchers


    @Before
    fun setup() {
        appDispatcher = MockCoroutineDispatchers()
        topCoinsRepository = MockToCoinRepo()
        MockitoAnnotations.openMocks(this)
        viewModel = TopCoinsViewModel(topCoinsRepository, appDispatcher)
    }

    @Test
    fun stateIsInitiallyLoading() =  runTest(appDispatcher.standardTestDispatcher) {
        assertEquals(TopCoinsUiState.Loading, viewModel.coinData.value)
    }

    @Test
    fun `coinDataState() - when coin data is emitted from repo- coinstate returns Success`() =
        runTest(appDispatcher.standardTestDispatcher) {
            val mockData = Resource.Success(TopCoinsData(
                listOf(Coin(2, "213", "123", 2.3, "", 2.3, 2.3))
            ))
            topCoinsRepository.mockEmit(mockData)

            // Then
            viewModel.coinData.test {
                assertEquals(TopCoinsUiState.Loading, awaitItem())
                awaitItem().let {
                    assertTrue(it is TopCoinsUiState.Success)
                    assertEquals(mockData.data, (it as TopCoinsUiState.Success).feed)

                }
                cancelAndIgnoreRemainingEvents()
            }
        }


    class MockToCoinRepo : TopCoinsRepository {
        private val mockFlow: MutableSharedFlow<Resource<TopCoinsData>> =
            MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

        override fun fetchTopCoins(): Flow<Resource<TopCoinsData>> {
            return mockFlow.map {
                val result = it
                result
            }
        }

        fun mockEmit(mockedData: Resource<TopCoinsData>) {
            mockFlow.tryEmit(mockedData)
        }

        fun emitException() {
            mockFlow.tryEmit(throw Exception())
        }


    }
}





