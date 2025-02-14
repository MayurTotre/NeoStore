package com.example.neostore.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.neostore.R
import com.example.neostore.databinding.FragmentDisplayCartItemsBinding
import com.example.neostore.databinding.FragmentSaveAddressBinding
import com.example.neostore.presentation.viewmodels.AddToCartViewModel
import com.example.neostore.presentation.viewmodels.OrderViewModel
import com.example.neostore.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSaveAddress : Fragment() {
    private lateinit var binding: FragmentSaveAddressBinding
    private val viewmodel: OrderViewModel by viewModels()
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var loader: CustomLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveAddressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferencesHelper = SharedPreferencesHelper(requireActivity())
        val accessToken = sharedPreferencesHelper.getData()
        loader = CustomLoader(requireContext())

        binding.btnSaveAddress.setOnClickListener {
            val etaddress = binding.etAddress.text.toString().trim()
            val city = binding.etCityName.text.toString().trim()
            val state = binding.etStateName.text.toString().trim()
            val zipCode = binding.etZipCode.text.toString().toInt()
            val country = binding.etCountry.text.toString().trim()

            val address = Address(
                id = 0,
                address = etaddress,
                landmark = "None" ,
                city = city,
                state = state,
                zipCode = zipCode,
                country = country
            )
            viewmodel.addAddress(address)
            findNavController().navigate(R.id.action_placeOrderFragment_to_fragmentSaveAddress)
        }


    }
}