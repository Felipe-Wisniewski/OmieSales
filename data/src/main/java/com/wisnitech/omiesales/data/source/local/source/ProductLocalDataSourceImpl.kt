package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.source.local.room.SaleDao
import kotlinx.coroutines.flow.Flow

internal class ProductLocalDataSourceImpl(private val dao: SaleDao) : ProductLocalDataSource {

    override suspend fun saveAllProducts(products: List<Product>) = dao.saveAllProducts(products)

    override suspend fun loadProducts(): List<Product> = dao.loadProducts()

    override suspend fun saveOrderItem(orderItem: OrderItem) = dao.saveOrderItem(orderItem)

    override suspend fun updateOrderItem(oldItem: OrderItem, newItem: OrderItem) {
        dao.deleteOrderItem(oldItem)
        dao.saveOrderItem(newItem)
    }

    override suspend fun loadOrder(): Flow<List<OrderItem>> = dao.loadOrder()

    override suspend fun deleteOrderItem(orderItem: OrderItem) = dao.deleteOrderItem(orderItem)
}