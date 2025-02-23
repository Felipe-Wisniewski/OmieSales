package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.source.local.source.SaleLocalDataSource

class SaleRepositoryImpl(
    private val saleLocal: SaleLocalDataSource
) : SaleRepository {

    override suspend fun addSale(sale: Sale) = saleLocal.saveSale(sale)

    override suspend fun removeSale(saleId: Long) = saleLocal.deleteSale(saleId)

    override suspend fun addProductsOnSale(saleProducts: List<SaleProduct>) =
        saleLocal.saveProductsOnSale(saleProducts)

    override suspend fun getSales(): List<SumSales> {
        return saleLocal.loadSumOfSales()
    }

    override suspend fun getProductListBySaleId(saleId: Long) =
        saleLocal.loadProductListBySaleId(saleId)
}