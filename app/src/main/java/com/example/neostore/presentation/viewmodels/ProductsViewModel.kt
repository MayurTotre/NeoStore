package com.example.neostore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neostore.domain.model.ProductRequest
import com.example.neostore.domain.model.ProductsResponse
import com.example.neostore.domain.repository.ProductsRepository
import com.example.neostore.domain.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase): ViewModel() {

        private val _productsList = MutableLiveData<Result<ProductsResponse>>()
        val productList: LiveData<Result<ProductsResponse>> = _productsList

        fun getProducts(productRequest: ProductRequest){
            viewModelScope.launch{
                productsUseCase.invoke(productRequest)
                    .onStart {
                        Log.d("products", "Started Products API call")
                    }
                    .catch { exception ->
                        Log.d("products", "${exception.message}")
                        _productsList.postValue(Result.failure(exception))
                    }
                    .collect{productResponse ->
                        Log.d("products", "${productResponse.toString()}")
                        _productsList.postValue(Result.success(productResponse))
                    }
            }
        }
}