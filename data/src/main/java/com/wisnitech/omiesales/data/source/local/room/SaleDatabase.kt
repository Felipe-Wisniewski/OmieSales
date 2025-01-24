package com.wisnitech.omiesales.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct

@Database(
    entities = [Customer::class, Product::class, Sale::class, SaleProduct::class, OrderItem::class],
    version = 1,
    exportSchema = false
)
abstract class SaleDatabase : RoomDatabase() {
    abstract fun getSaleDao(): SaleDao
}