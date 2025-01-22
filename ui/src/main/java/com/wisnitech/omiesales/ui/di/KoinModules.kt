package com.wisnitech.omiesales.ui.di

import com.wisnitech.omiesales.data.repository.CustomerRepository
import com.wisnitech.omiesales.data.repository.CustomerRepositoryImpl
import org.koin.dsl.module



val modules = module {

    // home vm
    // factory sales repository

    factory<CustomerRepository> { CustomerRepositoryImpl(get()) }

}
