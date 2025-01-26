package com.wisnitech.omiesales.ui.sale_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.SaleProductClient
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.repository.SaleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaleDetailsViewModel(private val saleRepository: SaleRepository) : ViewModel() {

    private val _sumSales = MutableLiveData<SumSales>()
    val sumSales: LiveData<SumSales> get() = _sumSales

    private val _saleItems = MutableLiveData<List<SaleProductClient>>()
    val saleItems: LiveData<List<SaleProductClient>> get() = _saleItems

    private val _itemsQuantity = MutableLiveData<Int>()
    val itemsQuantity: LiveData<Int> get() = _itemsQuantity

    fun setSumSales(sumSales: SumSales) {
        _sumSales.value = sumSales
    }

    fun getProductListBySaleId(saleId: Long) = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) {
            saleRepository.getProductListBySaleId(saleId)
        }

        _saleItems.value = result
        setItemsQuantity(result)
    }

    private fun setItemsQuantity(result: List<SaleProductClient>) {
        var quantity = 0
        result.forEach {
            quantity += it.productQuantity
        }.apply {
            _itemsQuantity.value = quantity
        }
    }
}