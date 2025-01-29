package com.example.neostore.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.neostore.R
import com.example.neostore.databinding.FragmentRegisterBinding
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.presentation.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var selectedGender: String = " "
    val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {

            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            val phoneNo = binding.etPhoneNo.text.toString().trim()

            binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
                val selectedRadioButton: RadioButton = binding.root.findViewById(checkedId)
                selectedGender = selectedRadioButton.text.toString()
            }

            val request = RegistrationRequest(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                gender = selectedGender,
                phoneNo = phoneNo
            )

            viewModel.registerUser(request)

            viewModel.registrationState.observe(viewLifecycleOwner){result ->
                result?.onSuccess {
                    Toast.makeText(requireContext(), it.user_msg, Toast.LENGTH_SHORT).show()
                    Log.d("response", result.toString())
                }?.onFailure {
                    Toast.makeText(requireContext(), "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    Log.d("response", result.toString())

                }

            }
        }

    }

}