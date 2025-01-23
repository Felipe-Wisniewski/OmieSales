package com.wisnitech.omiesales.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: Int,
    val name: String,
    val price: Double,
    val priceUnit: PriceUnit,
    val category: String
) : Parcelable {

    enum class PriceUnit(val value: String) {
        @SerializedName("Un")
        UNIT("Un"),

        @SerializedName("Kg")
        KG("Kg")
    }
}
