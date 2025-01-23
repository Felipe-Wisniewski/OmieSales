package com.wisnitech.omiesales.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val saleRepository: SaleRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _sales = MutableLiveData<List<SumSales>>()
    val sale:LiveData<List<SumSales>> get() = _sales

    fun getSales() = viewModelScope.launch{
        withContext(Dispatchers.IO) {
            _sales.postValue(saleRepository.getSales())
        }
    }

    fun updateProductsFromRemote() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            productRepository.updateProductsFromRemote()
        }
    }
}