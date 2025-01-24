package com.wisnitech.omiesales.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.ui.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun getAllProducts() = viewModelScope.launch {
        _status.value = Status.LOADING

        val result = withContext(Dispatchers.IO) {
            productRepository.getProducts()
        }

        if (result.isNotEmpty()) {
            _products.value = result
            _status.value = Status.SUCCESS
        }
    }

    fun setOrderItem(product: Product, quantity: Int) = viewModelScope.launch{
        val item = OrderItem(
            productId = product.id,
            productName = product.name,
            productPrice = product.price,
            quantity = quantity,
            total = totalValueOfProducts(product.price, quantity)
        )

        withContext(Dispatchers.IO) {
            productRepository.addOrderItem(item)
        }
    }

    private fun totalValueOfProducts(price: Double, quantity: Int) = quantity * price
}