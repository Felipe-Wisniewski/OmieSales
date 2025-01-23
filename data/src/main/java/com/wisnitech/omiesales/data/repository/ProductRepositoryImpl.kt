package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.source.local.source.ProductLocalDataSource
import com.wisnitech.omiesales.data.source.remote.source.ProductRemoteDataSource

class ProductRepositoryImpl(
    private val productLocal: ProductLocalDataSource,
    private val productRemote: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun addProduct(product: Product) {
        productLocal.saveProduct(product)
    }

    override suspend fun getProducts(): List<Product> {
        return try {
            val result = productRemote.loadProducts()
            addAllProducts(result)

            productLocal.loadProducts()
        } catch (e: Exception) {
            e.printStackTrace()
            productLocal.loadProducts()
        }
    }

    override suspend fun getProduct(id: Int): Product {
        return productLocal.loadProduct(id)
    }

    override suspend fun removeProduct(product: Product) {
        productLocal.deleteProduct(product)
    }

    private suspend fun addAllProducts(products: List<Product>) {
        productLocal.saveAllProducts(products)
    }
}