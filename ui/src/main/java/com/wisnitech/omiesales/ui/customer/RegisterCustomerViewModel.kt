package com.wisnitech.omiesales.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.omiesales.data.model.Customer
import com.wisnitech.omiesales.data.repository.CustomerRepository
import com.wisnitech.omiesales.ui.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterCustomerViewModel(private val customerRepository: CustomerRepository) : ViewModel() {

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> get() = _isRegistered

    private val _customerName = MutableLiveData<String>()
    val customerName: LiveData<String> get() = _customerName

    private val _invalidName = MutableLiveData<Boolean>()
    val invalidName: LiveData<Boolean> get() = _invalidName

    private val _customerPhone = MutableLiveData<String>()
    val customerPhone: LiveData<String> get() = _customerPhone

    private val _invalidPhone = MutableLiveData<Boolean>()
    val invalidPhone: LiveData<Boolean> get() = _invalidPhone

    private val _customerId = MutableLiveData<Event<Long>>()
    val customerId: LiveData<Event<Long>> get() = _customerId

    fun setCustomerName(name: String) {
        _customerName.value = name
    }

    fun setCustomerPhone(phone: String) {
        _customerPhone.value = phone
    }

    fun setIsRegistered(isRegistered: Boolean) {
        _isRegistered.value = isRegistered
    }

    fun setCustomer() {
        if (isRegistered.value == true) getCustomerByPhone()
        else setNewCustomer()
    }

    private fun setNewCustomer() = viewModelScope.launch {
        if (validFields()) {
            val customer = Customer(
                name = customerName.value ?: "",
                phone = customerPhone.value ?: "",
                registerDate = "23/01/2025"
            )

            val id = withContext(Dispatchers.IO) {
                customerRepository.addCustomer(customer)
            }

            _customerId.value = Event(id)
        }
    }

    fun getCustomerByPhone() = viewModelScope.launch {
        validFields()

        invalidPhone.value?.let {
            if (it) {
                customerPhone.value?.let { phone ->
                    val result = withContext(Dispatchers.IO) {
                        customerRepository.getCustomerByPhoneNumber(phone)
                    }
                    _customerName.value = result.name
                    _customerId.value = Event(result.id)
                }
            }
        }
    }

    private fun validFields(): Boolean {
        val nameValid = customerName.value?.let { it.length > 2 } ?: false
        val phoneValid = customerPhone.value?.let { it.length == 11 } ?: false
        val isRegister = isRegistered.value == true

        _invalidName.value = if (!isRegister) nameValid else null
        _invalidPhone.value = phoneValid

        return nameValid && phoneValid
    }
}