package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.source.local.source.ProductLocalDataSource

class ProductRepositoryImpl(
    private val productLocal: ProductLocalDataSource
) : ProductRepository {

    override suspend fun addProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun addAllProducts(products: List<Product>) {
        TODO("Not yet implemented")
    }

    override suspend fun getProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(id: Int): Product {
        TODO("Not yet implemented")
    }

    override suspend fun removeProduct(id: Int) {
        TODO("Not yet implemented")
    }
}