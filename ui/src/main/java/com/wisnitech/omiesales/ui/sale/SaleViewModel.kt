package com.wisnitech.omiesales.ui.sale

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaleViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _customerName = MutableLiveData<String>()
    val customerName: LiveData<String> get() = _customerName

    private val _orderTotalItems = MutableLiveData<String>()
    val orderTotalItems: LiveData<String> get() = _orderTotalItems

    private val _orderTotalValue = MutableLiveData<Double>()
    val orderTotalValue: LiveData<Double> get() = _orderTotalValue

    init {
        getTotalItemsAndValue()
    }

    private fun getTotalItemsAndValue() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            productRepository.getOrder().collect { items ->
                var totalItems = 0
                var totalValue = 0.0

                items.forEach {
                    totalItems += it.quantity
                    totalValue += it.total
                }

                _orderTotalItems.postValue("Items: $totalItems")
                _orderTotalValue.postValue(totalValue)
            }
        }
    }

    fun setCustomerName(customerName: String) {
        _customerName.value = customerName
    }

    fun removeAllItemsFromCart() = viewModelScope.launch {
        withContext(Dispatchers.IO) { productRepository.removeOrderItems() }
    }
}