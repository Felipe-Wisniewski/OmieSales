package com.wisnitech.omiesales.ui.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.ui.utils.Status
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProductsViewModel
    private lateinit var productRepository: ProductRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        productRepository = mockk(relaxed = true)
        viewModel = ProductsViewModel(productRepository)
    }

    @Test
    fun `getAllProducts should update LiveData correctly`() = runTest {
        val mockProducts = listOf(
            Product(1, "Product 1", "", 10.0, Product.PriceUnit.UNIT, ""),
            Product(2, "Product 2", "", 20.0, Product.PriceUnit.UNIT, "")
        )

        coEvery { productRepository.getProducts() } returns mockProducts

        val statusObserver = mockk<Observer<Status>>(relaxed = true)
        val productsObserver = mockk<Observer<List<Product>>>(relaxed = true)

        viewModel.status.observeForever(statusObserver)
        viewModel.products.observeForever(productsObserver)

        viewModel.getAllProducts()

        advanceUntilIdle()

        verifyOrder {
            statusObserver.onChanged(Status.LOADING)
            productsObserver.onChanged(mockProducts)
            statusObserver.onChanged(Status.SUCCESS)
        }
    }

    @Test
    fun `setOrderItem should call repository with correct data`() = runTest {
        val product = Product(1, "Product 1", "", 10.0, Product.PriceUnit.UNIT, "")
        val quantity = 2

        coEvery { productRepository.addOrderItem(any()) } just Runs

        viewModel.setOrderItem(product, quantity)

        coVerify {
            productRepository.addOrderItem(withArg {
                assertEquals(product.id, it.productId)
                assertEquals(product.name, it.productName)
                assertEquals(product.price, it.productPrice)
                assertEquals(product.priceUnit.value, it.productPriceUnit)
                assertEquals(quantity, it.quantity)
                assertEquals(20.0, it.total)
            })
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
