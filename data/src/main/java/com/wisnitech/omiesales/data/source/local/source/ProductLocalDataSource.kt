package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Product

interface ProductLocalDataSource {
    suspend fun saveProduct(product: Product)
    suspend fun saveAllProducts(products: List<Product>)
    suspend fun loadProducts(): List<Product>
    suspend fun loadProduct(id:Int):Product
    suspend fun deleteProduct(product: Product)
}