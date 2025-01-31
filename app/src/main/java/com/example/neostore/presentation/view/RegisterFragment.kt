package com.example.neostore.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
    lateinit var loader: CustomLoader
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader = CustomLoader(requireContext())


        (activity as AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_left_arrow_foreground)
            title = "REGISTER"
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    android.R.id.home -> {
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton: RadioButton = binding.root.findViewById(checkedId)
            selectedGender = selectedRadioButton.text.toString()
        }

        binding.btnRegister.setOnClickListener {

            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            val phoneNo = binding.etPhoneNo.text.toString().trim()



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
            loader.showLoader()
            viewModel.registrationState.observe(viewLifecycleOwner){result ->
                result?.onSuccess {
                    loader.dismiss()
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