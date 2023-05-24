package com.karan.flow.demo

import app.cash.turbine.test
import com.karan.coingecko.demo.data.local.StorageRepostory
import com.karan.coingecko.demo.ui.MainViewModel
import com.karan.coingecko.demo.ui.auth.screens.LoginState
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

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    lateinit var storageRepo: TestStorageRepository

    private lateinit var appDispatcher: MockCoroutineDispatchers


    @Before
    fun setup() {
        appDispatcher = MockCoroutineDispatchers()
        storageRepo = TestStorageRepository()
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(storageRepo, appDispatcher)
    }

    @Test
    fun stateIsInitiallyLoading() = runTest(appDispatcher.standardTestDispatcher) {
        assertEquals(LoginState.Loading, viewModel.isUserLoggedIn.value)
    }

    @Test
    fun `isUserLoggedIn() - isLogin Success`() =
        runTest(appDispatcher.standardTestDispatcher) {
            val mockData = true
            storageRepo.updateLoginState(mockData)

            // Then
            viewModel.isUserLoggedIn.test {
                assertEquals(LoginState.Loading, awaitItem())
                awaitItem().let {
                    assertTrue(it is LoginState.LoggedIn)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }


    @Test
    fun `isUserLoggedIn() - is Not Logged In`() =
        runTest(appDispatcher.standardTestDispatcher) {
            val mockData = false
            storageRepo.updateLoginState(mockData)

            // Then
            viewModel.isUserLoggedIn.test {
                assertEquals(LoginState.Loading, awaitItem())
                awaitItem().let {
                    assertTrue(it is LoginState.NotLoggedIn)
                }
                cancelAndIgnoreRemainingEvents()
            }
        }

    /**
     * Mocked Storage Repo because the Flow in
     * viewmodel is a upstream one and there is no trigger inside it
     */
    class TestStorageRepository : StorageRepostory {
        private val mockFlow: MutableSharedFlow<Boolean> =
            MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


        override suspend fun updateLoginState(isLoggedIn: Boolean) {
            mockFlow.tryEmit(isLoggedIn)
        }

        override fun fetchUserLoginState(): Flow<Boolean> {
            return mockFlow.map {
                val result = it
                result
            }
        }


    }
}





