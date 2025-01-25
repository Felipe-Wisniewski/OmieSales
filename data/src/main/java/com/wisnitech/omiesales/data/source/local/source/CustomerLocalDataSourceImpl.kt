package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.source.local.room.SaleDao

class CustomerLocalDataSourceImpl(private val dao: SaleDao) : CustomerLocalDataSource {

    override suspend fun saveCustomer(customer: Customer) = dao.saveCustomer(customer)

    override suspend fun loadCustomerByPhoneNumber(phoneNumber: String) =
        dao.loadCustomerByPhoneNumber(phoneNumber)
}