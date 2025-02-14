package com.example.neostore.presentation.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.neostore.domain.usecase.OrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.neostore.presentation.view.Address

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderuseCase: OrderUseCase
): ViewModel(){

    private val _addressList = MutableLiveData<List<Address>>()
    val addressList: LiveData<List<Address>> = _addressList

    fun getAddress(){
        viewModelScope.launch {
            _addressList.postValue(orderuseCase.invokegetAddress())
        }
    }

    fun addAddress(address: Address){
        viewModelScope.launch {
            orderuseCase.invokeaddAddress(address)
        }
    }

    fun deleteAddress(id: Int){
        viewModelScope.launch {
            orderuseCase.invokeDeleteAddress(id)
        }
    }
}