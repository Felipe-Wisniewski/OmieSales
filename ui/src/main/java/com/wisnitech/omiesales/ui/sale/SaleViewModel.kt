package com.wisnitech.omiesales.ui.sale

import androidx.lifecycle.ViewModel
import com.wisnitech.omiesales.data.repository.CustomerRepository
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository

class SaleViewModel(
    private val customerRepository: CustomerRepository,
    private val productRepository: ProductRepository,
    private val saleRepository: SaleRepository
) : ViewModel() {


}