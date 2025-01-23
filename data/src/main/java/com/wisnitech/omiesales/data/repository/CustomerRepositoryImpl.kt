package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.source.local.source.CustomerLocalDataSource
import com.wisnitech.omiesales.data.source.remote.source.CustomerRemoteDataSource

class CustomerRepositoryImpl(
    private val customerLocal: CustomerLocalDataSource,
    private val customerRemote: CustomerRemoteDataSource
) : CustomerRepository {

    override suspend fun addCustomer(customer: Customer) = customerLocal.saveCustomer(customer)

    override suspend fun getCustomerByPhoneNumber(phoneNumber: String) =
        customerLocal.loadCustomerByPhoneNumber(phoneNumber)


//    override suspend fun getCustomers(): List<Customer> {
//        return try {
//            val result = customerRemote.loadCustomers()
//            addAllCustomers(result)
//
//            customerLocal.loadCustomers()
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            customerLocal.loadCustomers()
//        }
//    }

//    override suspend fun removeCustomer(customer: Customer) {
//        customerLocal.deleteCustomer(customer)
//    }

//    private suspend fun addAllCustomers(customers: List<Customer>) {
//        customerLocal.saveCustomers(customers)
//    }
}