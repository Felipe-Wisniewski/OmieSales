package com.wisnitech.omiesales.ui.sale

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.Sale
import com.wisnitech.omiesales.data.model.SaleProduct
import com.wisnitech.omiesales.data.repository.ProductRepository
import com.wisnitech.omiesales.data.repository.SaleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaleViewModel(
    private val saleRepository: SaleRepository
) : ViewModel() {

    private val _saleId = MutableLiveData<Long>()
    val saleId: LiveData<Long> get() = _saleId

    private val _customerName = MutableLiveData<String>()
    val customerName: LiveData<String> get() = _customerName

    private val _saleFinalized = MutableLiveData<Unit>()
    val saleFinalized: LiveData<Unit> get() = _saleFinalized

    fun setCustomerName(customerName: String) {
        _customerName.value = customerName
    }

    fun setNewSale(customerId: Long) = viewModelScope.launch {
        val sale = Sale(customerId = customerId, saleDate = "23/01/2025")

        _saleId.value = withContext(Dispatchers.IO) {
            saleRepository.addSale(sale)
        }
    }

    fun addProductToSale() = viewModelScope.launch {
        saleId.value?.let {
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
            _saleFinalized.value = Unit
        }
    }
}