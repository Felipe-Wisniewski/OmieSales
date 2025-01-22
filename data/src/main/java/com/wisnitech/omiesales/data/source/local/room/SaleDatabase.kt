package com.wisnitech.omiesales.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.model.Sale

@Database(
    entities = [Customer::class, Product::class, Sale::class],
    version = 1,
    exportSchema = false
)
abstract class SaleDatabase : RoomDatabase() {
    abstract fun getSaleDao(): SaleDao
}