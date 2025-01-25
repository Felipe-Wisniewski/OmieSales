package com.wisnitech.omiesales.ui.sale

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import com.wisnitech.omiesales.ui.utils.getCurrentDate
import com.wisnitech.omiesales.ui.utils.toCurrencyNoCoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

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

    private val _saleDeleted = MutableLiveData<Unit>()
    val saleDeleted: LiveData<Unit> get() = _saleDeleted

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

                _orderTotalItems.postValue("Items: $totalItems")
                _orderTotalValue.postValue("R$ ${totalValue.toCurrencyNoCoin()}")
            }
        }
    }

    fun setCustomerName(customerName: String) {
        _customerName.value = customerName
    }

    fun setNewSale(customerId: Long) = viewModelScope.launch {
        val sale = Sale(
            customerId = customerId,
            saleDate = Calendar.getInstance().getCurrentDate()
        )

        _saleId.value = withContext(Dispatchers.IO) {
            saleRepository.addSale(sale)
        }
    }

    fun deleteSale() = viewModelScope.launch {
        saleId.value?.let {
            _saleDeleted.value = withContext(Dispatchers.IO) {
                saleRepository.removeSale(it)
            }
        }
    }
}