package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SaleProductClient
import com.wisnitech.omiesales.data.model.SumSales

interface SaleLocalDataSource {
    suspend fun saveSale(sale: Sale): Long?
    suspend fun deleteSale(saleId: Long)
    suspend fun saveProductsOnSale(saleProducts: List<SaleProduct>)
    suspend fun loadSumOfSales(): List<SumSales>
    suspend fun loadProductListBySaleId(saleId: Long): List<SaleProductClient>
}