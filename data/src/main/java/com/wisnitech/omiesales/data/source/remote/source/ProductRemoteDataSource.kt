package com.wisnitech.omiesales.data.source.remote.source

import com.wisnitech.omiesales.data.model.Product

interface ProductRemoteDataSource {
    suspend fun loadProducts(): List<Product>
}