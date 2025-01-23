package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SumSales

interface SaleLocalDataSource {
    suspend fun saveSale(sale: Sale)
    suspend fun saveAllSales(sales: List<Sale>)
    suspend fun loadSumOfSales(): List<SumSales>
    suspend fun loadSale(id: Int): Sale
    suspend fun deleteSale(sale: Sale)
}