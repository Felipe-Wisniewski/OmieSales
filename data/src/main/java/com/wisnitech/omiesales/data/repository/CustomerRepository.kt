package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Customer

interface CustomerRepository {
    suspend fun addCustomer(customer: Customer)
    suspend fun getCustomers(): List<Customer>
    suspend fun removeCustomer(customer: Customer)
}