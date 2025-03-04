package com.wisnitech.omiesales.ui.utils

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import com.wisnitech.omiesales.ui.R

@BindingAdapter("is_visible_view")
fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("set_sale_count")
fun AppCompatTextView.setSaleCount(count: Int?) {
    count?.let {
        val concatenate = "Sales: $it"
        text = concatenate
    }
}

@BindingAdapter("set_toolbar_title")
fun MaterialToolbar.setToolbarTitle(title: String?) {
    title?.let { this.title = title }
}

@BindingAdapter("set_total_currency_value")
fun AppCompatTextView.setTotalCurrencyValue(total: Double?) {
    total?.let {
        val concatenate = "Total: R$ ${it.toCurrencyNoCoin()}"
        text = concatenate
    }
}

@BindingAdapter("set_currency_value")
fun AppCompatTextView.setValueWithCurrencySymbol(total: Double?) {
    total?.let {
        val concatenate = "R$ ${it.toCurrencyNoCoin()}"
        text = concatenate
    }
}

@BindingAdapter("set_formatted_date")
fun AppCompatTextView.setFormattedDate(date: String?) {
    date?.let { text = it.getFormattedDate() }
}

@BindingAdapter("set_item_sale_number")
fun AppCompatTextView.setItemSaleNumber(saleId: Long?) {
    saleId?.let {
        val concatenate = "Sale n°: $saleId"
        text = concatenate
    }
}

@BindingAdapter("set_search_visibility")
fun TextInputLayout.setSearchEndIcon(isRegistered: Boolean) {
    if (isRegistered) {
        endIconMode = TextInputLayout.END_ICON_CUSTOM
        endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_search)
        endIconContentDescription = "TODO"
    } else {
        endIconMode = TextInputLayout.END_ICON_NONE
        endIconDrawable = null
    }
}

@BindingAdapter("set_product_code")
fun AppCompatTextView.setProductCode(code: String?) {
    code?.let {
        val concatenate = "Cod: $code"
        text = concatenate
    }
}

@BindingAdapter(value = ["product_price", "product_unit"], requireAll = true)
fun AppCompatTextView.setProductPrice(price: Double?, unit: String?) {
    price?.let {
        val priceFormatted = "R$ ${price.toCurrencyNoCoin()}"
        val concatenate = unit?.let { "$priceFormatted / $it" }
        text = concatenate
    }
}

@BindingAdapter("set_item_cart_quantity")
fun AppCompatTextView.setItemCartQuantity(items: Int?) {
    items?.let {
        val concatenate = "Qtd: $it"
        text = concatenate
    }
}

@BindingAdapter(value = ["set_item_cart_item_price", "set_item_cart_item_unit"], requireAll = true)
fun AppCompatTextView.setItemCartItemPrice(price: Double?, unit: String?) {
    price?.let {
        val concatenate = "Price${(" $unit")}: R$ ${it.toCurrencyNoCoin()}"
        text = concatenate
    }
}