package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.source.local.room.SaleDao

class SaleLocalDataSourceImpl(private val dao: SaleDao) : SaleLocalDataSource {

    override suspend fun saveSale(sale: Sale) = dao.saveSale(sale)

    override suspend fun loadSales() = dao.loadSales()

    override suspend fun loadSale(id: Int) = dao.loadSale(id)

    override suspend fun deleteSale(sale: Sale) = dao.deleteSale(sale)
}