package com.wisnitech.omiesales.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Sale(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "sale_customer_id")
    val customerId: Int,
    @ColumnInfo(name = "sale_total_items")
    val totalItems: Int,
    @ColumnInfo(name = "sale_total")
    val totalSale: Double,
    @ColumnInfo(name = "sale_date")
    val saleDate:String,
    @ColumnInfo(name = "sale_products")
    val products: List<Product>
) : Parcelable
