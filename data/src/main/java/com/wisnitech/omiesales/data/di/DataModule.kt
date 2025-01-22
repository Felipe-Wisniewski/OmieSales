package com.wisnitech.omiesales.data.di

import androidx.room.Room
import com.wisnitech.omiesales.data.source.local.room.SaleDao
import com.wisnitech.omiesales.data.source.local.room.SaleDatabase
import com.wisnitech.omiesales.data.source.local.source.CustomerLocalDataSource
import com.wisnitech.omiesales.data.source.local.source.CustomerLocalDataSourceImpl
import com.wisnitech.omiesales.data.source.local.source.ProductLocalDataSource
import com.wisnitech.omiesales.data.source.local.source.ProductLocalDataSourceImpl
import com.wisnitech.omiesales.data.source.local.source.SaleLocalDataSource
import com.wisnitech.omiesales.data.source.local.source.SaleLocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single<SaleLocalDataSource> { SaleLocalDataSourceImpl(get()) }
    single<ProductLocalDataSource> { ProductLocalDataSourceImpl(get()) }
    single<CustomerLocalDataSource> { CustomerLocalDataSourceImpl(get()) }

    single<SaleDao> {
        val db = Room.databaseBuilder(
            androidApplication(),
            SaleDatabase::class.java,
            "omie_sales_database"
        ).build()

        db.getSaleDao()
    }
}