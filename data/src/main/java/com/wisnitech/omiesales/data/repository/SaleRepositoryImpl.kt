package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.source.local.source.SaleLocalDataSource

class SaleRepositoryImpl(
    private val saleLocal: SaleLocalDataSource
) : SaleRepository {

    override suspend fun addSale(sale: Sale) {
        TODO("Not yet implemented")
    }

    override suspend fun getSales(): List<Sale> {
        TODO("Not yet implemented")
    }

    override suspend fun getSale(id: Int): Sale {
        TODO("Not yet implemented")
    }

    override suspend fun removeSale(id: Int) {
        TODO("Not yet implemented")
    }
}