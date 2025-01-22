package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Sale

interface SaleLocalDataSource {
    suspend fun saveSale(sale: Sale)
    suspend fun loadSales(): List<Sale>
    suspend fun loadSale(id: Int): Sale
    suspend fun deleteSale(sale: Sale)
}