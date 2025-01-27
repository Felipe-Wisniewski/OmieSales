package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    suspend fun saveAllProducts(products: List<Product>)
    suspend fun loadProducts(): List<Product>
    suspend fun saveOrderItem(orderItem: OrderItem)
    suspend fun updateOrderItem(oldItem: OrderItem, newItem: OrderItem)
    suspend fun loadOrder(): Flow<List<OrderItem>>
    suspend fun deleteOrderItem(orderItem: OrderItem)
    suspend fun deleteOrderItems()
}