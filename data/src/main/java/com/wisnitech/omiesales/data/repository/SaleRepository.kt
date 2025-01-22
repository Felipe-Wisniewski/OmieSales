package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Sale

interface SaleRepository {
    suspend fun addSale(sale: Sale)
    suspend fun getSales(): List<Sale>
    suspend fun getSale(id: Int): Sale
    suspend fun removeSale(id: Int)
}