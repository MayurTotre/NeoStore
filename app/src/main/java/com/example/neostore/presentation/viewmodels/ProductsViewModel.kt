package com.example.neostore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neostore.domain.model.ProductDetailsRequest
import com.example.neostore.domain.model.ProductDetailsResponse
import com.example.neostore.domain.model.ProductRatingRequest
import com.example.neostore.domain.model.ProductRatingResponse
import com.example.neostore.domain.model.ProductRequest
import com.example.neostore.domain.model.ProductsResponse
import com.example.neostore.domain.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
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

    private val _productDetails = MutableLiveData<Result<ProductDetailsResponse>>()
    val productDetails: LiveData<Result<ProductDetailsResponse>> = _productDetails

    fun getProductDetails(productDetailsRequest: ProductDetailsRequest){
        viewModelScope.launch {
            productsUseCase.invokeProductDetails(productDetailsRequest)
                .onStart {
                    Log.d("product details", "Started product details API Call")
                }
                .catch {exception ->
                    Log.d("products", "${exception.message}")
                    _productDetails.postValue(Result.failure(exception))
                }
                .collect{ productDetailsResponse ->
                    Log.d("products", "${productDetailsResponse.toString()}")
                    _productDetails.postValue(Result.success(productDetailsResponse))
                }
        }
    }

    private val _setProductRating = MutableLiveData<Result<ProductRatingResponse>>()
    val setProductRating: LiveData<Result<ProductRatingResponse>> = _setProductRating

    fun setProductRatring(productRatingRequest: ProductRatingRequest){
        viewModelScope.launch{
            productsUseCase.invokeProductRating(productRatingRequest)
                .onStart {
                    Log.d("product rating", "Started product rating API Call")
                }
                .catch {exception ->
                    Log.d("product rating", "${exception}")
                    _setProductRating.postValue(Result.failure(exception))
                }
                .collect{productRatingResponse ->
                    Log.d("product rating", "${productRatingResponse.toString()}")
                    _setProductRating.postValue(Result.success(productRatingResponse))
                }
        }
    }
    }