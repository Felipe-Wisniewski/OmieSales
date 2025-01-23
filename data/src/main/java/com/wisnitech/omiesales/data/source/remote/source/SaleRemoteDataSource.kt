package com.wisnitech.omiesales.data.source.remote.source

import com.wisnitech.omiesales.data.model.Sale

interface SaleRemoteDataSource {
    suspend fun loadSales(): List<Sale>
}