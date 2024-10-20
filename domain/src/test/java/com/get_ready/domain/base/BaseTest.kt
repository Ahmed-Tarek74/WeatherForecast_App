package com.get_ready.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseTest {

    // TestDispatcher that will be used in all coroutine-based tests
    protected val testDispatcher = StandardTestDispatcher()


    @Before
    open fun setUp() {
        Dispatchers.setMain(testDispatcher)  // Set testDispatcher as the Main dispatcher
    }

    @After
    fun tearDownBase() {
        Dispatchers.resetMain()  // Reset Main dispatcher
        testDispatcher.scheduler.advanceUntilIdle()  // Cleanup coroutine execution
    }
}