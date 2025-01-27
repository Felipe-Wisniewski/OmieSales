package com.wisnitech.omiesales.data.model

import android.os.Parcelable
import com.wisnitech.omiesales.data.model.Product.PriceUnit
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaleProductClient(
    val productName: String,
    val productQuantity: Int,
    val productPrice: Double,
    val priceUnit: PriceUnit,
    val totalValue: Double
) : Parcelable
