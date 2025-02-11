package com.example.neostore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.AddToCartResponse
import com.example.neostore.domain.usecase.AddToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToCartViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private val _addToCart = MutableLiveData<Result<AddToCartResponse>>()
    val addToCart: LiveData<Result<AddToCartResponse>> = _addToCart

    fun addToCart(header: String, addToCartRequest: AddToCartRequest){
        viewModelScope.launch {
            addToCartUseCase.invoke(header, addToCartRequest)
                .onStart {
                    Log.d("add to cart", "Started Add to Cart API call")
                }
                .catch {exception ->
                    Log.d("add to cart", "${exception.message}")
                    _addToCart.postValue(Result.failure(exception))
                }
                .collect{response ->
                    Log.d("add to cart", "${response.data}")
                    _addToCart.postValue(Result.success(response))
                }
        }
    }
}