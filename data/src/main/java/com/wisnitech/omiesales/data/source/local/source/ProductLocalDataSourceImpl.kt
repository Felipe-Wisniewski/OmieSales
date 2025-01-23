package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.source.local.room.SaleDao

class ProductLocalDataSourceImpl(private val dao: SaleDao) : ProductLocalDataSource {

//    override suspend fun saveProduct(product: Product) = dao.saveProduct(product)

    override suspend fun saveAllProducts(products: List<Product>) = dao.saveAllProducts(products)

    override suspend fun loadProducts(): List<Product> = dao.loadProducts()

//    override suspend fun loadProduct(id: Int): Product = dao.loadProduct(id)

//    override suspend fun deleteProduct(product: Product) = dao.deleteProduct(product)
}