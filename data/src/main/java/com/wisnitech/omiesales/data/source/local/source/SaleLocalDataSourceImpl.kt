package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.source.local.room.SaleDao

internal class SaleLocalDataSourceImpl(private val dao: SaleDao) : SaleLocalDataSource {

    override suspend fun saveSale(sale: Sale) = dao.saveSale(sale)

    override suspend fun deleteSale(saleId: Long) = dao.deleteSale(saleId)

    override suspend fun saveProductsOnSale(saleProducts: List<SaleProduct>) =
        dao.saveProductsOnSale(saleProducts)

    override suspend fun loadSumOfSales() = dao.loadSumOfSales()

    override suspend fun loadProductListBySaleId(saleId: Long) = dao.loadProductListBySaleId(saleId)
}