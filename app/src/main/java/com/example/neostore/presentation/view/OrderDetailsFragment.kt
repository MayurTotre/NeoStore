package com.example.neostore.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.neostore.R
import com.example.neostore.databinding.FragmentDisplayCartItemsBinding
import com.example.neostore.databinding.FragmentOrderDetailsBinding
import com.example.neostore.databinding.FragmentSaveAddressBinding
import com.example.neostore.domain.model.OrderDetailsResponse
import com.example.neostore.presentation.viewmodels.AddToCartViewModel
import com.example.neostore.presentation.viewmodels.OrderDetailsViewModel
import com.example.neostore.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailsFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailsBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var loader: CustomLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferencesHelper = SharedPreferencesHelper(requireActivity())
        val accessToken = sharedPreferencesHelper.getData()
        loader = CustomLoader(requireContext())

    }
}