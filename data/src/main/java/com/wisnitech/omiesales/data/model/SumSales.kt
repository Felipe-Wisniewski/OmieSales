package com.wisnitech.omiesales.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SumSales(
    val saleId: Long,
    val saleDate: String,
    val customerName: String,
    val saleValue: Double
) : Parcelable