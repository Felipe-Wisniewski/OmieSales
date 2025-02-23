package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SaleProductClient
import com.wisnitech.omiesales.data.model.SumSales

interface SaleRepository {
    suspend fun addSale(sale: Sale): Long?
    suspend fun removeSale(saleId: Long)
    suspend fun addProductsOnSale(saleProducts:List<SaleProduct>)
    suspend fun getSales(): List<SumSales>
    suspend fun getProductListBySaleId(saleId: Long): List<SaleProductClient>
}