package com.wisnitech.omiesales.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SaleProductClient(
    @Embedded
    val sale: Sale,
    @Relation(parentColumn = "customerId", entityColumn = "id")
    val customer: Customer,
    @Relation(
        entity = Product::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = SaleProduct::class,
            parentColumn = "saleId",
            entityColumn = "productId"
        )
    )
    val products: List<Product>
)
