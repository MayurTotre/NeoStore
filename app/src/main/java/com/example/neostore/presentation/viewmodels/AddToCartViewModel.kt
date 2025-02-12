package com.example.neostore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.AddToCartResponse
import com.example.neostore.domain.model.DeleteProductRequest
import com.example.neostore.domain.model.DeleteProductResponse
import com.example.neostore.domain.model.DisplayCartResponse
import com.example.neostore.domain.model.EditCartRequest
import com.example.neostore.domain.model.EditCartResponse
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

    private val _displayCartItems = MutableLiveData<Result<DisplayCartResponse>>()
    val displaCartItems: LiveData<Result<DisplayCartResponse>> = _displayCartItems

    fun displayCartItems(header: String){
        viewModelScope.launch {
            addToCartUseCase.invokeCartItems(header)
                .onStart {
                    Log.d("Display Cart", "Started Display Cart API call")
                }
                .catch { exception ->
                    Log.d("Display Cart", "${exception.message}")
                    _displayCartItems.postValue(Result.failure(exception))
                }
                .collect{ response ->
                    Log.d("Display Cart", "${response}")
                    _displayCartItems.postValue(Result.success(response))
                }
        }
    }

    private val _deleteProduct = MutableLiveData<Result<DeleteProductResponse>>()
    val deleteProduct: LiveData<Result<DeleteProductResponse>> = _deleteProduct
    fun deleteProduct(header: String, deleteProductRequest: DeleteProductRequest){
        viewModelScope.launch {
            addToCartUseCase.invokeDeleteProduct(header, deleteProductRequest)
                .onStart {
                    Log.d("Delete Product", "Started Delete Product API call")
                }
                .catch { exception ->
                    Log.d("Delete Product", "${exception.message}")
                    _deleteProduct.postValue(Result.failure(exception))
                }
                .collect{ response ->
                    Log.d("Delete Product", "${response}")
                    _deleteProduct.postValue(Result.success(response))
                }
        }
    }

    private val _editCart = MutableLiveData<Result<EditCartResponse>>()
    val editCart: LiveData<Result<EditCartResponse>> = _editCart

    fun editCart(header: String, editCartRequest: EditCartRequest){
        viewModelScope.launch {
            addToCartUseCase.invokeEditCart(header, editCartRequest)
                .onStart {
                    Log.d("Edit Cart", "Started Edit Cart API call")
                }
                .catch { exception ->
                    Log.d("Edit Cart", "${exception.message}")
                    _editCart.postValue(Result.failure(exception))
                }
                .collect{ response ->
                    Log.d("Edit Cart", "${response}")
                    _editCart.postValue(Result.success(response))
                }
        }
    }
}