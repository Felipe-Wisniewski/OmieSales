package com.wisnitech.omiesales.data.source.local.source

import com.wisnitech.omiesales.data.model.Customer

interface CustomerLocalDataSource {
    suspend fun saveCustomer(customer: Customer): Long
    suspend fun loadCustomerByPhoneNumber(phoneNumber: String): Customer
//    suspend fun saveCustomers(customers: List<Customer>)
//    suspend fun loadCustomers(): List<Customer>
//    suspend fun deleteCustomer(customer: Customer)
}