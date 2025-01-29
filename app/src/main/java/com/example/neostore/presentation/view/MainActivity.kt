package com.example.neostore.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.neostore.databinding.ActivityMainBinding
import com.example.neostore.domain.model.RegistrationRequest
import com.example.neostore.presentation.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedGender: String = " "
    val viewModel: RegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            val phoneNo = binding.etPhoneNo.text.toString().trim()

            binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
                val selectedRadioButton: RadioButton = findViewById(checkedId)
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

            viewModel.registrationState.observe(this){result ->
                result?.onSuccess {
                    Toast.makeText(this, it.user_msg, Toast.LENGTH_SHORT).show()
                    Log.d("response", result.toString())
                }?.onFailure {
                    Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    Log.d("response", result.toString())

                }

            }
        }
    }
}