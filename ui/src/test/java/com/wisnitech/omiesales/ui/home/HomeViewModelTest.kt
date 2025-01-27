package com.wisnitech.omiesales.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import com.wisnitech.omiesales.ui.utils.Status
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var saleRepository: SaleRepository
    private lateinit var productRepository: ProductRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        saleRepository = mockk(relaxed = true)
        productRepository = mockk(relaxed = true)
        viewModel = HomeViewModel(saleRepository, productRepository)
    }

    @Test
    fun `getAllSales should update LiveData correctly`() = runTest {
        val mockSales = listOf(
            SumSales(1, "", "", 100.0),
            SumSales(2, "", "", 200.0)
        )

        coEvery { saleRepository.getSales() } returns mockSales

        val statusObserver = mockk<Observer<Status>>(relaxed = true)
        val salesObserver = mockk<Observer<List<SumSales>>>(relaxed = true)
        val saleCountObserver = mockk<Observer<Int>>(relaxed = true)
        val salesValueObserver = mockk<Observer<Double>>(relaxed = true)

        viewModel.status.observeForever(statusObserver)
        viewModel.sales.observeForever(salesObserver)
        viewModel.saleCount.observeForever(saleCountObserver)
        viewModel.salesValue.observeForever(salesValueObserver)

        viewModel.getAllSales()

        advanceUntilIdle()

        verifyOrder {
            statusObserver.onChanged(Status.LOADING)
            salesObserver.onChanged(mockSales)
            saleCountObserver.onChanged(2)
            salesValueObserver.onChanged(300.0)
            statusObserver.onChanged(Status.SUCCESS)
        }
    }

    @Test
    fun `updateProductsFromRemote should call repository`() = runTest {
        coEvery { productRepository.updateProductsFromRemote() } just Runs

        viewModel.getAllSales()

        advanceUntilIdle()

        coVerify { productRepository.updateProductsFromRemote() }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}