package com.wisnitech.omiesales.ui.di

import com.wisnitech.omiesales.data.repository.CustomerRepository
import com.wisnitech.omiesales.data.repository.CustomerRepositoryImpl
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.ProductRepositoryImpl
import com.wisnitech.omiesales.data.repository.SaleRepository
import com.wisnitech.omiesales.data.repository.SaleRepositoryImpl
import com.wisnitech.omiesales.ui.customer.RegisterCustomerViewModel
import com.wisnitech.omiesales.ui.home.HomeViewModel
import com.wisnitech.omiesales.ui.products.ProductsViewModel
import com.wisnitech.omiesales.ui.sale.SaleViewModel
import com.wisnitech.omiesales.ui.order_cart.OrderCartViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModules = module {

    viewModel { HomeViewModel(get(), get()) }
    viewModel { RegisterCustomerViewModel(get()) }
    viewModel { SaleViewModel(get(), get()) }
    viewModel { OrderCartViewModel(get(), get()) }
    viewModel { ProductsViewModel(get()) }

    factory<SaleRepository> { SaleRepositoryImpl(get(), get()) }
    factory<CustomerRepository> { CustomerRepositoryImpl(get(), get()) }
    factory<ProductRepository> { ProductRepositoryImpl(get(), get()) }

}
