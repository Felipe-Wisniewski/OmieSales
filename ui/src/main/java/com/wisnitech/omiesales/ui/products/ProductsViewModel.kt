package com.wisnitech.omiesales.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel(
    private val productRepository: ProductRepository,
    private val saleRepository: SaleRepository
) : ViewModel() {

    private val _saleId = MutableLiveData<Long>()
    val saleId: LiveData<Long> get() = _saleId

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun setSaleId(saleId: Long) {
        _saleId.value = saleId
    }

    fun getAllProducts() = viewModelScope.launch {
        _products.value = withContext(Dispatchers.IO) {
            productRepository.getProducts()
        }
    }


}