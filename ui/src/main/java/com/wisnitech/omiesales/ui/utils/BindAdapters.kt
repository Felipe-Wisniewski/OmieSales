package com.wisnitech.omiesales.ui.utils

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.wisnitech.omiesales.ui.R

@BindingAdapter("is_visible_view")
fun View.setVisibility(isVisible:Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
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
    code?.let { text = "Cod: $code" }
}

@BindingAdapter(value = ["product_price", "product_unit"], requireAll = true)
fun AppCompatTextView.setProductPrice(price: Double?, unit: String?) {
    price?.let {
        val priceFormatted = price.toCurrencyNoCoin()
        val p = "R$ $priceFormatted"
        val concat = unit?.let { "$p / $it" }
        text = concat
    }
}