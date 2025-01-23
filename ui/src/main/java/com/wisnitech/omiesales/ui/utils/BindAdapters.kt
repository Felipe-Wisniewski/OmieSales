package com.wisnitech.omiesales.ui.utils

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.wisnitech.omiesales.ui.R

@BindingAdapter("set_search_visibility")
fun TextInputLayout.setSearchEndIcon(isRegistered:Boolean) {
    if (isRegistered) {
        endIconMode = TextInputLayout.END_ICON_CUSTOM
        endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_search)
        endIconContentDescription = "TODO"
    } else {
        endIconMode = TextInputLayout.END_ICON_NONE
        endIconDrawable = null
    }
}