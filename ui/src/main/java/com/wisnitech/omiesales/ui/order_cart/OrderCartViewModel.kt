package com.wisnitech.omiesales.ui.order_cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import com.wisnitech.omiesales.ui.utils.Event
import com.wisnitech.omiesales.ui.utils.getCurrentDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class OrderCartViewModel(
    private val productRepository: ProductRepository,
    private val saleRepository: SaleRepository
) : ViewModel() {

    private var _customerId: Long = 0

    private val _orderItems = MutableLiveData<List<OrderItem>>()
    val orderItems: LiveData<List<OrderItem>> get() = _orderItems

    private val _orderPlaced = MutableLiveData<Event<Unit>>()
    val orderPlaced: LiveData<Event<Unit>> get() = _orderPlaced

    init {
        updateCart()
    }

    fun setCustomerId(customerId: Long) {
        _customerId = customerId
    }

    private fun updateCart() = viewModelScope.launch {
        val order = withContext(Dispatchers.IO) {
            productRepository.getOrder()
        }

        order.collect { items ->
            _orderItems.value = items
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
                productPriceUnit = orderItem.productPriceUnit,
                quantity = quantity,
                total = totalValueOfProducts(orderItem.productPrice, quantity)
            )
            productRepository.updateOrderItem(orderItem, newItem)
        }
    }

    fun setNewSale() = viewModelScope.launch {
        val sale = Sale(
            customerId = _customerId,
            saleDate = Calendar.getInstance().getCurrentDate()
        )

        val saleId = async(Dispatchers.IO) {
            saleRepository.addSale(sale)
        }

        placeOrder(saleId.await())
    }

    private fun placeOrder(saleId: Long?) = viewModelScope.launch {
        if (saleId != null) {
            val order = orderItems.value

            order?.forEach { item ->
                val sale = SaleProduct(saleId, item.productId, item.quantity)
                withContext(Dispatchers.IO) {
                    saleRepository.addProductOnSale(sale)
                }
            }.apply {
                removeAllItemsFromCart()
                _orderPlaced.value = Event(Unit)
            }
        }
    }

    private fun removeAllItemsFromCart() = viewModelScope.launch {
        withContext(Dispatchers.IO) { productRepository.removeOrderItems() }
    }

    private fun totalValueOfProducts(price: Double, quantity: Int) = quantity * price
}