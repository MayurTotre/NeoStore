package com.example.neostore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neostore.data.repository.OrderDetailsRepositoryImpl
import com.example.neostore.domain.model.OrderDetailsResponse
import com.example.neostore.domain.usecase.OrderDetailsUseCase
import com.example.neostore.domain.usecase.OrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val orderDetailsUseCase: OrderDetailsUseCase
): ViewModel() {

    private val _orderDetails = MutableLiveData<Result<OrderDetailsResponse>>()
    val orderDetails: LiveData<Result<OrderDetailsResponse>> = _orderDetails

    fun getOrderDetails(header: String, address: String){
        viewModelScope.launch {
            orderDetailsUseCase.invokeOrderDetails(header, address)
                .onStart {
                    Log.d("Order Details", "Started Order Details API call")
                }
                .catch {exception ->
                    Log.d("Order Details", "${exception.message}")
                    _orderDetails.postValue(Result.failure(exception))
                }
                .collect{response ->
                    Log.d("Order Details", "${response}")
                    _orderDetails.postValue(Result.success(response))
                }

        }
    }
}