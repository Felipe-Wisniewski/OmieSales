package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.source.local.source.ProductLocalDataSource
import com.wisnitech.omiesales.data.source.remote.source.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productLocal: ProductLocalDataSource,
    private val productRemote: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun updateProductsFromRemote() {
        return try {
            val result = productRemote.loadProducts()
            addAllProducts(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getProducts() = productLocal.loadProducts()

    override suspend fun addOrderItem(orderItem: OrderItem) = productLocal.saveOrderItem(orderItem)

    override fun getOrder(): Flow<List<OrderItem>> = productLocal.loadOrder()

    override suspend fun removeOrderItem(orderItem: OrderItem) =
        productLocal.deleteOrderItem(orderItem)

    override suspend fun removeOrderItems() = productLocal.deleteOrderItems()

    private suspend fun addAllProducts(products: List<Product>) {
        productLocal.saveAllProducts(products)
    }
}