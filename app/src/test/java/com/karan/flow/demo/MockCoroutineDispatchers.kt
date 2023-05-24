package com.karan.flow.demo

import com.karan.coingecko.demo.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class MockCoroutineDispatchers : AppDispatchers {
    val standardTestDispatcher = StandardTestDispatcher()
    val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    override val ui: CoroutineDispatcher = standardTestDispatcher

    override val io: CoroutineDispatcher = standardTestDispatcher

    override val unconfined: CoroutineDispatcher = unconfinedTestDispatcher

    override val default: CoroutineDispatcher = standardTestDispatcher
}