package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SumSales

interface SaleLocalDataSource {
    suspend fun saveSale(sale: Sale): Long?
    suspend fun deleteSale(saleId: Long)
    suspend fun saveProductOnSale(saleProduct: SaleProduct): Long
    suspend fun loadSumOfSales(): List<SumSales>
}