package com.wisnitech.omiesales.data.source.remote.source

import com.wisnitech.omiesales.data.model.Customer

interface CustomerRemoteDataSource {
    suspend fun loadCustomers(): List<Customer>
}