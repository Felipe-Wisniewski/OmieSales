package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.source.local.source.SaleLocalDataSource
import com.wisnitech.omiesales.data.source.remote.source.SaleRemoteDataSource

class SaleRepositoryImpl(
    private val saleLocal: SaleLocalDataSource,
    private val saleRemote: SaleRemoteDataSource
) : SaleRepository {

    override suspend fun addSale(sale: Sale) = saleLocal.saveSale(sale)

    override suspend fun removeSale(saleId: Long) = saleLocal.deleteSale(saleId)

    override suspend fun addProductOnSale(saleProduct: SaleProduct) =
        saleLocal.saveProductOnSale(saleProduct)

    override suspend fun getSales(): List<SumSales> {
        return saleLocal.loadSumOfSales()
    }
}