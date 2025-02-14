package com.example.neostore.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostore.R
import com.example.neostore.databinding.FragmentPlaceOrderBinding
import com.example.neostore.databinding.FragmentSaveAddressBinding
import com.example.neostore.interfaces.OnClickDoAction
import com.example.neostore.presentation.adapter.AddressAdapter
import com.example.neostore.presentation.viewmodels.OrderDetailsViewModel
import com.example.neostore.presentation.viewmodels.OrderViewModel
import com.example.neostore.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceOrderFragment : Fragment(), OnClickDoAction {
    private lateinit var binding: FragmentPlaceOrderBinding
    private val orderViewModel: OrderDetailsViewModel by viewModels()
    private val viewmodel: OrderViewModel by viewModels()
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var loader: CustomLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaceOrderBinding.inflate(layoutInflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferencesHelper = SharedPreferencesHelper(requireActivity())
        val accessToken = sharedPreferencesHelper.getData()
        loader = CustomLoader(requireContext())
        viewmodel.getAddress()

        viewmodel.addressList.observe(viewLifecycleOwner, Observer {response ->
            binding.rvAddress.adapter = AddressAdapter(response, this)
            binding.rvAddress.layoutManager = LinearLayoutManager(requireContext())
        })

            binding.btnAddAddress.setOnClickListener{
                findNavController().navigate(R.id.action_placeOrderFragment_to_fragmentSaveAddress)
            }
        binding.btnPlaceOrder.setOnClickListener {
            orderViewModel.getOrderDetails(header = accessToken!!, address = "Thane" )
            orderViewModel.orderDetails.observe(viewLifecycleOwner, Observer {response ->
                response.let { result ->
                    result.onSuccess { data ->
                        Toast.makeText(requireContext(), "${data}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

    }

    override fun onClickGetDetails(id: Int) {
        viewmodel.deleteAddress(id)
        viewmodel.getAddress()
    }
}