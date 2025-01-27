package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.source.local.source.CustomerLocalDataSource

class CustomerRepositoryImpl(
    private val customerLocal: CustomerLocalDataSource
) : CustomerRepository {

    override suspend fun addCustomer(customer: Customer) = customerLocal.saveCustomer(customer)

    override suspend fun getCustomerByPhoneNumber(phoneNumber: String) =
        customerLocal.loadCustomerByPhoneNumber(phoneNumber)
}