package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.source.local.room.SaleDao

class CustomerLocalDataSourceImpl(private val dao: SaleDao) : CustomerLocalDataSource {

    override suspend fun saveCustomer(customer: Customer) = dao.saveCustomer(customer)

    override suspend fun loadCustomerByPhoneNumber(phoneNumber: String) =
        dao.loadCustomerByPhoneNumber(phoneNumber)

//    override suspend fun saveCustomers(customers: List<Customer>) = dao.saveCustomers(customers)
//
//    override suspend fun loadCustomers() = dao.loadCustomers()
//
//    override suspend fun deleteCustomer(customer: Customer) = dao.deleteCustomer(customer)
}