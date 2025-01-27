package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.model.Product.PriceUnit.UNIT
import com.wisnitech.omiesales.data.source.local.source.ProductLocalDataSource
import com.wisnitech.omiesales.data.source.remote.source.ProductRemoteDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.Mockito.`when`

class ProductRepositoryImplTest {

    private lateinit var productLocal: ProductLocalDataSource
    private lateinit var productRemote: ProductRemoteDataSource
    private lateinit var repository: ProductRepositoryImpl

    @Before
    fun setUp() {
        productLocal = mock(ProductLocalDataSource::class.java)
        productRemote = mock(ProductRemoteDataSource::class.java)
        repository = ProductRepositoryImpl(productLocal, productRemote)
    }

    @Test
    fun `updateProductsFromRemote should save products to local`() = runTest {
            val products = listOf(
                Product(1, "P1", "Product A", 1.0, UNIT, ""),
                Product(2, "P2", "Product B", 2.0, UNIT, "")
            )

            `when`(productRemote.loadProducts()).thenReturn(products)

            repository.updateProductsFromRemote()

            verify(productRemote).loadProducts()
            verify(productLocal).saveAllProducts(products)
        }

    @Test
    fun `updateProductsFromRemote should handle exception when remote fetch fails`() = runTest {
        `when`(productRemote.loadProducts()).thenThrow(RuntimeException("Network error"))

        repository.updateProductsFromRemote()

        verify(productRemote).loadProducts()
        verifyZeroInteractions(productLocal)
    }

    @Test
    fun `getProducts should return products from local`() = runTest {
        val products = listOf(
            Product(1, "P1", "Product A", 1.0, UNIT, ""),
            Product(2, "P2", "Product B", 2.0, UNIT, "")
        )
        `when`(productLocal.loadProducts()).thenReturn(products)

        val result = repository.getProducts()

        verify(productLocal).loadProducts()
        assert(result == products)
    }

    @Test
    fun `addOrderItem should save order item to local`() = runTest {
        val orderItem = OrderItem(1, 1, "Product A", 1.0, "", 1, 1.0)

        repository.addOrderItem(orderItem)

        verify(productLocal).saveOrderItem(orderItem)
    }

    @Test
    fun `updateOrderItem should update order item in local`() = runTest {
        val oldItem = OrderItem(1, 1, "Product A", 1.0, "", 1, 1.0)
        val newItem = OrderItem(1, 1, "Product A", 1.0, "", 2, 1.0)

        repository.updateOrderItem(oldItem, newItem)

        verify(productLocal).updateOrderItem(oldItem, newItem)
    }

    @Test
    fun `getOrder should return order from local`() = runTest {
        val orderItems = listOf(OrderItem(1, 1, "Product A", 1.0, "", 2, 1.0))
        `when`(productLocal.loadOrder()).thenReturn(flowOf(orderItems))

        val result = repository.getOrder()

        verify(productLocal).loadOrder()
        result.collect {
            assert(it == orderItems)
        }
    }

    @Test
    fun `removeOrderItem should delete order item from local`() = runTest {
        val orderItem = OrderItem(1, 1, "Product A", 1.0, "", 2, 1.0)

        repository.removeOrderItem(orderItem)

        verify(productLocal).deleteOrderItem(orderItem)
    }

    @Test
    fun `removeOrderItems should delete all order items from local`() = runTest {
        repository.removeOrderItems()

        verify(productLocal).deleteOrderItems()
    }
}