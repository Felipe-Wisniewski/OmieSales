package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SumSales

interface SaleRepository {
    suspend fun addSale(sale: Sale): Long
    suspend fun addProductOnSale(saleProduct: SaleProduct): Long
    suspend fun getSales(): List<SumSales>
//    suspend fun getSale(id: Int): Sale
//    suspend fun removeSale(sale: Sale)
}