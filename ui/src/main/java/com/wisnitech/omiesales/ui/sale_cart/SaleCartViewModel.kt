package com.wisnitech.omiesales.ui.sale_cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaleCartViewModel(
    private val productRepository: ProductRepository,
    private val saleRepository: SaleRepository
) : ViewModel() {

    private var saleId: Long = 0

    private val _orderItems = MutableLiveData<List<OrderItem>>()
    val orderItems: LiveData<List<OrderItem>> get() = _orderItems

    init {
        updateCart()
    }

    private fun updateCart() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            productRepository.getOrder().collect { orderItems ->
                _orderItems.postValue(orderItems)
            }
        }
    }

    fun addProductToSale() = viewModelScope.launch {
        if (saleId != 0L) {
            val p1 = SaleProduct(1, 1, 2)
            val p2 = SaleProduct(1, 11, 1)
            val p3 = SaleProduct(1, 31, 5)
            val p4 = SaleProduct(1, 12, 2)

            val task = async(Dispatchers.IO) {
                saleRepository.addProductOnSale(p1)
                saleRepository.addProductOnSale(p2)
                saleRepository.addProductOnSale(p3)
                saleRepository.addProductOnSale(p4)
            }

            val result = task.await()
            Log.d("FLMWG", "TASK AWAIT : $result")
//            _saleFinalized.value = Unit
        }
    }

    fun deleteOrderItems() {

    }

    fun deleteSale() {

    }
}