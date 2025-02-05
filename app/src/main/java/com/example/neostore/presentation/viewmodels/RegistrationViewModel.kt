package com.example.neostore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.neostore.R
import com.example.neostore.domain.model.LoginRequest
import com.example.neostore.domain.model.LoginResponse
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.domain.model.RegistrationResponse
import com.example.neostore.domain.model.UserDataResponse
import com.example.neostore.domain.repository.UserRepository
import com.example.neostore.domain.usecase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
//    private val repository: UserRepository
) : ViewModel() {

    private val _registrationState = MutableLiveData<Result<RegistrationResponse>>()
    val registrationState: LiveData<Result<RegistrationResponse>> = _registrationState

    fun registerUser(request: RegistrationRequest) {
        viewModelScope.launch {
            registerUserUseCase.registerinvoke(request)
                .catch { exception ->
                    _registrationState.postValue(Result.failure(exception))
                }
                .collect { response ->
                    _registrationState.postValue(Result.success(response))
                }
//            repository.registerUser(request)

        }
    }

    private val _loginState = MutableLiveData<Result<LoginResponse>>()
    val loginState: LiveData<Result<LoginResponse>> = _loginState

    fun loginUser(request: LoginRequest) {
        viewModelScope.launch {
            registerUserUseCase.logininvoke(request)
                .onStart {
                    Log.d("Login", "Started API call")
                }
                .catch { exception ->
                    Log.d("Login", "Error: ${exception.message}")
                    _loginState.postValue(Result.failure(exception))
                }
                .collect { response ->
                    Log.d("Login", "Success: $response")
                    _loginState.postValue(Result.success(response))
                }
        }

    }

    private val _userData = MutableLiveData<Result<UserDataResponse>>()
    val getUserData: LiveData<Result<UserDataResponse>> = _userData

    fun getUserData(access_token: String) {
        viewModelScope.launch {
            registerUserUseCase.getUserData(access_token)
                .onStart {
                    Log.d("User Data", "Started API Call")
                }
                .catch { exception ->
                    Log.d("User Data", "Error: ${exception.message}")
                    _userData.postValue(Result.failure(exception))
                }
                .collect { response ->
                    Log.d("User Data", "Error: ${response.toString()}")
                    _userData.postValue(Result.success(response))
                }
        }
    }

}
