package com.wisnitech.omiesales.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Customer(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "customer_name")
    val name: String,
    @ColumnInfo(name = "register_date")
    val registerDate: String
) : Parcelable
