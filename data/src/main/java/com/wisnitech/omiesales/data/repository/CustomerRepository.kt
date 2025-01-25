package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Customer

interface CustomerRepository {
    suspend fun addCustomer(customer: Customer): Long
    suspend fun getCustomerByPhoneNumber(phoneNumber: String): Customer
}