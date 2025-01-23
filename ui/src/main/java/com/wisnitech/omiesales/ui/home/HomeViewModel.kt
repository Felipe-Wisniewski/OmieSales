package com.wisnitech.omiesales.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.data.repository.SaleRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val saleRepository: SaleRepository) : ViewModel() {

    private val _sales = MutableLiveData<List<SumSales>>()
    val sale:LiveData<List<SumSales>> get() = _sales

    fun getSales() = viewModelScope.launch{
        _sales.value = saleRepository.getSales()
    }
}