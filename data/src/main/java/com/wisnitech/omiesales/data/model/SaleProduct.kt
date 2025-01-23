package com.wisnitech.omiesales.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["saleId", "productId"],
    foreignKeys = [
        ForeignKey(
            entity = Sale::class,
            parentColumns = ["id"],
            childColumns = ["saleId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SaleProduct(
    val saleId: Long,
    val productId: Long,
    val quantity: Int
)
