package com.wisnitech.omiesales.data.repository

import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.source.local.source.CustomerLocalDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CustomerRepositoryImplTest {

    private lateinit var customerLocal: CustomerLocalDataSource
    private lateinit var repository: CustomerRepositoryImpl

    @Before
    fun setUp() {
        customerLocal = mock(CustomerLocalDataSource::class.java)
        repository = CustomerRepositoryImpl(customerLocal)
    }

    @Test
    fun `addCustomer should save customer to local data source`() = runTest {
        val customer = Customer(1, "Felipe", "123456789", "27/01/2025")

        repository.addCustomer(customer)

        verify(customerLocal).saveCustomer(customer)
    }

    @Test
    fun `getCustomerByPhoneNumber should return customer when found`() = runTest {
        val phoneNumber = "123456789"
        val customer = Customer(1, "Felipe", phoneNumber, "27/01/2025")
        `when`(customerLocal.loadCustomerByPhoneNumber(phoneNumber)).thenReturn(customer)

        val result = repository.getCustomerByPhoneNumber(phoneNumber)

        verify(customerLocal).loadCustomerByPhoneNumber(phoneNumber)
        assert(result == customer)
    }

    @Test
    fun `getCustomerByPhoneNumber should return null when customer is not found`() = runTest {
        val phoneNumber = "123456789"
        `when`(customerLocal.loadCustomerByPhoneNumber(phoneNumber)).thenReturn(null)

        val result = repository.getCustomerByPhoneNumber(phoneNumber)

        verify(customerLocal).loadCustomerByPhoneNumber(phoneNumber)
        assert(result == null)
    }
}