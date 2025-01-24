package com.wisnitech.omiesales.ui.sale

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import com.wisnitech.omiesales.ui.utils.toCurrencyNoCoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaleViewModel(
    private val saleRepository: SaleRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _saleId = MutableLiveData<Long>()
    val saleId: LiveData<Long> get() = _saleId

    private val _customerName = MutableLiveData<String>()
    val customerName: LiveData<String> get() = _customerName

    private val _orderTotalValue = MutableLiveData<String>()
    val orderTotalValue: LiveData<String> get() = _orderTotalValue

    private val _orderTotalItems = MutableLiveData<String>()
    val orderTotalItems: LiveData<String> get() = _orderTotalItems

    private val _saleFinalized = MutableLiveData<Unit>()
    val saleFinalized: LiveData<Unit> get() = _saleFinalized

    init {
        getTotalItemsAndValue()
    }

    private fun getTotalItemsAndValue() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            productRepository.getOrder().collect { orderItems ->
                var totalItems = 0
                var totalValue = 0.0

                orderItems.forEach {
                    totalItems += it.quantity
                    totalValue += it.total
                }

                _orderTotalValue.postValue(totalValue.toCurrencyNoCoin())
                _orderTotalItems.postValue(totalItems.toString())
            }
        }
    }

    fun setCustomerName(customerName: String) {
        _customerName.value = customerName
    }

    fun setNewSale(customerId: Long) = viewModelScope.launch {
        val sale = Sale(customerId = customerId, saleDate = "23/01/2025")

        _saleId.value = withContext(Dispatchers.IO) {
            saleRepository.addSale(sale)
        }
    }
}