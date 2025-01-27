package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun updateProductsFromRemote()
    suspend fun getProducts(): List<Product>
    suspend fun addOrderItem(orderItem: OrderItem)
    suspend fun updateOrderItem(oldItem: OrderItem, newItem: OrderItem)
    suspend fun getOrder(): Flow<List<OrderItem>>
    suspend fun removeOrderItem(orderItem: OrderItem)
    suspend fun removeOrderItems()
}