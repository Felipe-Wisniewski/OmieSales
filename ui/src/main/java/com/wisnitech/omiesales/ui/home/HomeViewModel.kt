package com.wisnitech.omiesales.ui.home

import android.util.Log
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

class HomeViewModel(private val saleRepository: SaleRepository,private val productRepository: ProductRepository) : ViewModel() {

    private val _sales = MutableLiveData<List<SumSales>>()
    val sale:LiveData<List<SumSales>> get() = _sales

    fun insertSomeProductsInDatabase() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val result = productRepository.getProducts()
            Log.d("FLMWG","result: ${result.size}")
            Log.d("FLMWG","result: ${result.joinToString()}")
        }
    }

    fun getSales() = viewModelScope.launch{
        withContext(Dispatchers.IO) {
            _sales.postValue(saleRepository.getSales())
        }
    }
}