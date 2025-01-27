package com.wisnitech.omiesales.ui.order_cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import com.wisnitech.omiesales.ui.utils.Event
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
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
class OrderCartViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val productRepository = mockk<ProductRepository>(relaxed = true)
    private val saleRepository = mockk<SaleRepository>(relaxed = true)
    private lateinit var viewModel: OrderCartViewModel

    private val tesCoroutineDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(tesCoroutineDispatcher)
        viewModel = OrderCartViewModel(productRepository, saleRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `setCustomerId should update customerId`() {
        val customerId = 1234L
        viewModel.setCustomerId(customerId)

        // Accessing private variable for testing purposes
        val field = viewModel.javaClass.getDeclaredField("_customerId")
        field.isAccessible = true
        val result = field.get(viewModel) as Long

        assertEquals(customerId, result)
    }

    @Test
    fun `updateOrderItem should call removeItem if quantity is zero`() = runTest {
        val orderItem = OrderItem(1L, 1L, "Feijão", 10.0, "Un", 1, 10.0)

        coEvery { productRepository.removeOrderItem(orderItem) } just Runs

        viewModel.updateOrderItem(orderItem, 0)

        advanceUntilIdle()

        coVerify { productRepository.removeOrderItem(orderItem) }
    }

    @Test
    fun `updateOrderItem should call updateItem if quantity changes`() = runTest {
        val orderItem = OrderItem(1L, 1L, "Feijão", 10.0, "Un", 1, 10.0)

        coEvery { productRepository.updateOrderItem(any(), any()) } just Runs

        viewModel.updateOrderItem(orderItem, 5)

        advanceUntilIdle()

        coVerify {
            productRepository.updateOrderItem(
                orderItem,
                withArg {
                    assertEquals(5, it.quantity)
                    assertEquals(50.0, it.total)
                }
            )
        }
    }

    @Test
    fun `setNewSale should call addSale and placeOrder`() = runTest {
        val saleId = 1234L
        coEvery { saleRepository.addSale(any()) } returns saleId
        coEvery { saleRepository.addProductsOnSale(any()) } just Runs

        viewModel.setCustomerId(1L)
        viewModel.setNewSale()

        advanceUntilIdle()

        coVerify { saleRepository.addSale(withArg { assertEquals(1L, it.customerId) }) }
    }

    @Test
    fun `updateCart should update orderItems live data`() = runTest {
        val orderItems = listOf(OrderItem(1L, 2, "Prod 2", 10.0, "Un", 1, 10.0))
        val observer = mockk<Observer<List<OrderItem>>>(relaxed = true)
        viewModel.orderItems.observeForever(observer)

        coEvery { productRepository.getOrder() } returns flowOf(orderItems)
        viewModel.updateCart()

        advanceUntilIdle()

        verify { observer.onChanged(orderItems) }
    }

    @Test
    fun `placeOrder should trigger orderPlaced event`() = runTest {
        val orderItems = listOf(OrderItem(1L, 2, "Prod 2", 10.0, "Un", 1, 10.0))

        coEvery { productRepository.getOrder() } returns flowOf(orderItems)
        coEvery { saleRepository.addProductsOnSale(any()) } just Runs
        coEvery { productRepository.removeOrderItems() } just Runs

        val observer = mockk<Observer<Event<Unit>>>(relaxed = true)
        viewModel.orderPlaced.observeForever(observer)

        viewModel.setCustomerId(1L)
        viewModel.setNewSale()

        advanceUntilIdle()

        verify { observer.onChanged(any()) }
    }


}