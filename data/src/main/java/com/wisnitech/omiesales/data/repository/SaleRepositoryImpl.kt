package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.source.local.source.SaleLocalDataSource
import com.wisnitech.omiesales.data.source.remote.source.SaleRemoteDataSource

class SaleRepositoryImpl(
    private val saleLocal: SaleLocalDataSource,
    private val saleRemote: SaleRemoteDataSource
) : SaleRepository {

    override suspend fun addSale(sale: Sale) {
        saleLocal.saveSale(sale)
    }

    override suspend fun getSales(): List<SumSales> {
        /*return try {
            val result = saleRemote.loadSales()
            addAllSales(result)

            saleLocal.loadSumOfSales()

        } catch (e: Exception) {
            e.printStackTrace()
            saleLocal.loadSumOfSales()
        }*/
        return saleLocal.loadSumOfSales()
    }

    override suspend fun getSale(id: Int): Sale {
        return saleLocal.loadSale(id)
    }

    override suspend fun removeSale(sale: Sale) {
        saleLocal.deleteSale(sale)
    }

    private suspend fun addAllSales(sales: List<Sale>) {
        saleLocal.saveAllSales(sales)
    }
}