package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SumSales

interface SaleRepository {
    suspend fun addSale(sale: Sale)
    suspend fun getSales(): List<SumSales>
    suspend fun getSale(id: Int): Sale
    suspend fun removeSale(sale: Sale)
}