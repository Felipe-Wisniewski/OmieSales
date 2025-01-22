package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Product

interface ProductRepository {
    suspend fun addProduct(product: Product)
    suspend fun addAllProducts(products: List<Product>)
    suspend fun getProducts(): List<Product>
    suspend fun getProduct(id:Int):Product
    suspend fun removeProduct(id: Int)
}