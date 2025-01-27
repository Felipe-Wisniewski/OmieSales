package com.wisnitech.omiesales.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import com.wisnitech.omiesales.ui.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val saleRepository: SaleRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    private val _sales = MutableLiveData<List<SumSales>>()
    val sales: LiveData<List<SumSales>> get() = _sales

    private val _saleCount = MutableLiveData(0)
    val saleCount: LiveData<Int> get() = _saleCount

    private val _salesValue = MutableLiveData(0.0)
    val salesValue: LiveData<Double> get() = _salesValue

    init {
        updateProductsFromRemote()
    }

    fun getAllSales() = viewModelScope.launch {
        _status.value = Status.LOADING

        var total = 0.0

        val result = withContext(Dispatchers.IO) {
            saleRepository.getSales()
        }

        _sales.value = result
        _saleCount.value = result.size

        result.forEach { total += it.saleValue }
        _salesValue.value = total

        _status.value = Status.SUCCESS
    }

    private fun updateProductsFromRemote() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            productRepository.updateProductsFromRemote()
        }
    }
}