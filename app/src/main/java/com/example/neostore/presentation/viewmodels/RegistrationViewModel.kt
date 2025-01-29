package com.example.neostore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neostore.domain.model.LoginRequest
import com.example.neostore.domain.model.LoginResponse
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.domain.model.RegistrationResponse
import com.example.neostore.domain.repository.UserRepository
import com.example.neostore.domain.usecase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
//    private val repository: UserRepository
): ViewModel() {

    private val _registrationState = MutableLiveData<Result<RegistrationResponse>>()
    val registrationState: LiveData<Result<RegistrationResponse>> = _registrationState

    fun registerUser(request: RegistrationRequest){
        viewModelScope.launch {
            registerUserUseCase.registerinvoke(request)
                .catch {exception ->
                    _registrationState.value = Result.failure(exception)
                }

                .collect{response ->
                    _registrationState.value = Result.success(response)
                }
//            repository.registerUser(request)

        }
    }

    private val _loginState = MutableLiveData<Result<LoginResponse>>()
    val loginState: LiveData<Result<LoginResponse>> = _loginState

    fun loginUser(request: LoginRequest){
        viewModelScope.launch{
            registerUserUseCase.logininvoke(request)
                .catch { exception ->
                    Log.d("data", exception.toString())
                    _loginState.value = Result.failure(exception)
                }
                .collect{response ->
                    _loginState.value = Result.success(response)
                }

        }
    }

}