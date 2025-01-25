package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Customer

interface CustomerLocalDataSource {
    suspend fun saveCustomer(customer: Customer): Long
    suspend fun loadCustomerByPhoneNumber(phoneNumber: String): Customer?
}