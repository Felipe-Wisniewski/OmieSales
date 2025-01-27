package com.wisnitech.omiesales.ui.utils

import java.text.NumberFormat
import java.util.Currency

fun Double.toCurrencyNoCoin(): String {
    val currencyFormatter = NumberFormat.getInstance()
    currencyFormatter.minimumFractionDigits = 2
    currencyFormatter.maximumFractionDigits = 2
    currencyFormatter.currency = Currency.getInstance("BRL")
    return currencyFormatter.format(this)
}