package com.wisnitech.omiesales.ui.sale_cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import com.wisnitech.omiesales.ui.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaleCartViewModel(
    private val productRepository: ProductRepository,
    private val saleRepository: SaleRepository
) : ViewModel() {

    private var _saleId: Long = 0

    private val _orderItems = MutableLiveData<List<OrderItem>>()
    val orderItems: LiveData<List<OrderItem>> get() = _orderItems

    private val _orderPlaced = MutableLiveData<Event<Unit>>()
    val orderPlaced: LiveData<Event<Unit>> get() = _orderPlaced

    init {
        updateCart()
    }

    fun setSaleId(saleId: Long) {
        _saleId = saleId
    }

    private fun updateCart() = viewModelScope.launch {
        val orders = withContext(Dispatchers.IO) {
            productRepository.getOrder()
        }

        orders.collect { orderItems ->
            _orderItems.value = orderItems
        }
    }

    fun updateOrderItem(orderItem: OrderItem, quantity: Int) = viewModelScope.launch {
        when {
            quantity == 0 -> removeItem(orderItem)

            quantity != orderItem.quantity -> updateItem(orderItem, quantity)
        }
    }

    private fun removeItem(orderItem: OrderItem) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            productRepository.removeOrderItem(orderItem)
        }
    }

    private fun updateItem(orderItem: OrderItem, quantity: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val newItem = OrderItem(
                productId = orderItem.productId,
                productName = orderItem.productName,
                productPrice = orderItem.productPrice,
                quantity = quantity,
                total = totalValueOfProducts(orderItem.productPrice, quantity)
            )
            productRepository.updateOrderItem(orderItem, newItem)
        }
    }

    fun placeOrder() = viewModelScope.launch {
        if (_saleId != 0L) {

            val orders = orderItems.value

            orders?.forEach { item ->
                val sale = SaleProduct(_saleId, item.productId, item.quantity)

                val result = async(Dispatchers.IO) { saleRepository.addProductOnSale(sale) }

                // TODO("REMOVE ALL ITEMS")
                if (result.await() != 0L) {
                    withContext(Dispatchers.IO) { productRepository.removeOrderItem(item) }
                }
            }.apply {
                _orderPlaced.value = Event(Unit)
            }
        }
    }

    fun deleteSale() {

    }

    private fun totalValueOfProducts(price: Double, quantity: Int) = quantity * price
}