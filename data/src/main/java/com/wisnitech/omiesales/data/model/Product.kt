package com.wisnitech.omiesales.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Product(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "product_code")
    val code: Int,
    @ColumnInfo(name = "product_name")
    val name: String,
    @ColumnInfo(name = "product_price")
    val price: Double,
    @ColumnInfo(name = "price_unit")
    val priceUnit: PriceUnit,
    @ColumnInfo(name = "product_category")
    val category: String
) : Parcelable {

    enum class PriceUnit(val value: String) {
        @SerializedName("Un")
        UNIT("Un"),

        @SerializedName("Kg")
        KG("Kg")
    }
}
