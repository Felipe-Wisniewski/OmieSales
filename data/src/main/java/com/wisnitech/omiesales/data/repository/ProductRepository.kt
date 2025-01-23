package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Product

interface ProductRepository {
//    suspend fun addProduct(product: Product)
    suspend fun updateProductsFromRemote()
    suspend fun getProducts(): List<Product>
//    suspend fun getProduct(id:Int):Product
//    suspend fun removeProduct(product: Product)
}