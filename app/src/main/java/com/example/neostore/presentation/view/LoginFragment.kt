package com.example.neostore.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.neostore.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.neostore.databinding.FragmentLoginBinding
import com.example.neostore.domain.model.LoginRequest
import com.example.neostore.presentation.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loader = CustomLoader(requireContext())
        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            val request = LoginRequest(
                email = email,
                password = password
            )

            viewModel.loginUser(request)
            loader.showLoader()
            viewModel.loginState.observe(viewLifecycleOwner) { loginResponse ->
                loginResponse?.let { result ->
                    result.onSuccess { data ->
                        loader.dismiss()
                        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                        Toast.makeText(requireContext(), data.user_msg, Toast.LENGTH_SHORT).show()
                        Log.d("Login Success", "Response: $data")
                    }
                    result.onFailure { exception ->
                        Toast.makeText(requireContext(), exception.message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
                        Log.d("Login Failure", "Error: ${exception.message}")
                    }
                } ?: run {
                    Log.d("Login Failure", "loginResponse is null")
                }
            }

        }
        binding.addAccount.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}