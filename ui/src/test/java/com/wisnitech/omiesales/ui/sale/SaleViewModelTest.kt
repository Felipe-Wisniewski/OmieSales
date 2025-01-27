package com.wisnitech.omiesales.ui.sale

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.ui.utils.getOrAwaitValue
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SaleViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val productRepository = mockk<ProductRepository>()
    private lateinit var viewModel: SaleViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SaleViewModel(productRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `getTotalItemsAndValue should update orderTotalItems and orderTotalValue`() = runTest {
        val orderItems = listOf(
            OrderItem(1, 1, "ProdA", 25.0, "", 2, 50.0),
            OrderItem(2, 2, "Prod2", 30.0, "", 1, 30.0)
        )

        coEvery { productRepository.getOrder() } returns flowOf(orderItems)

        viewModel.getTotalItemsAndValue()

        advanceUntilIdle()

        val totalItems = viewModel.orderTotalItems.getOrAwaitValue()
        val totalValue = viewModel.orderTotalValue.getOrAwaitValue()

        assertEquals(totalItems, "Items: 3")
        assertEquals(totalValue, 80.0)
    }

    @Test
    fun `setCustomerName should update customerName`() {
        viewModel.setCustomerName("Felipe")

        val customerName = viewModel.customerName.getOrAwaitValue()

        assertEquals("Felipe", customerName)
    }

    @Test
    fun `removeAllItemsFromCart should call removeOrderItems from repository`() = runTest {
        coEvery { productRepository.removeOrderItems() } just Runs

        viewModel.removeAllItemsFromCart()
        advanceUntilIdle()

        coVerify { productRepository.removeOrderItems() }
    }
}