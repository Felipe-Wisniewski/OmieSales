package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.source.local.room.SaleDao

class SaleLocalDataSourceImpl(private val dao: SaleDao) : SaleLocalDataSource {

    override suspend fun saveSale(sale: Sale) = dao.saveSale(sale)

    override suspend fun saveProductOnSale(saleProduct: SaleProduct) =
        dao.saveProductOnSale(saleProduct)

    override suspend fun loadSumOfSales() = dao.loadSumOfSales()
}