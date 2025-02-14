package com.example.neostore.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostore.R
import com.example.neostore.databinding.FragmentDisplayCartItemsBinding
import com.example.neostore.domain.model.DeleteProductRequest
import com.example.neostore.domain.model.EditCartRequest
import com.example.neostore.interfaces.OnClickAddOrRemove
import com.example.neostore.interfaces.OnClickDoAction
import com.example.neostore.presentation.adapter.DisplayCartAdapter
import com.example.neostore.presentation.viewmodels.AddToCartViewModel
import com.example.neostore.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DisplayCartItemsFragment : Fragment(), OnClickDoAction, OnClickAddOrRemove {
    private lateinit var binding: FragmentDisplayCartItemsBinding
    private val viewmodel: AddToCartViewModel by viewModels()
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var loader: CustomLoader
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayCartItemsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferencesHelper = SharedPreferencesHelper(requireActivity())
         val accessToken = sharedPreferencesHelper.getData()
        loader = CustomLoader(requireContext())


        viewmodel.displayCartItems(accessToken!!)
        loader.showLoader()

        viewmodel.displaCartItems.observe(viewLifecycleOwner, Observer {response ->
            response.let { result ->
                result.onSuccess { data ->
                    loader.dismiss()
                    val list = data.data
                    val displayCartAdapter = DisplayCartAdapter(list, this, this)
                    binding.rvDisplayCartItems.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvDisplayCartItems.adapter = displayCartAdapter
                    binding.tvDisplayPrice.text = data.total.toString()
                }
            }

        })

        binding.btnOrderNow.setOnClickListener {
            findNavController().navigate(R.id.action_displayCartItems_to_placeOrderFragment)
        }

    }

    override fun onClickGetDetails(id: Int) {
        val deleteProductRequest = DeleteProductRequest(id)
        viewmodel.deleteProduct(sharedPreferencesHelper.getData()!!,deleteProductRequest)
        viewmodel.deleteProduct.observe(viewLifecycleOwner, Observer { response ->
            response.let { result ->
                result.onSuccess { data ->
                    Toast.makeText(requireContext(), "deleted", Toast.LENGTH_SHORT).show()
                    lifecycleScope.launch {
                        loader.showLoader()
                        delay(2000)
                        viewmodel.displayCartItems(sharedPreferencesHelper.getData()!!)
                    }
                }
            }
        })

    }

    override fun onClickAddOrRemove(id: Int, quantity: Int) {
        val editCartRequest = EditCartRequest(id,  quantity)
        viewmodel.editCart(sharedPreferencesHelper.getData()!!, editCartRequest)
        viewmodel.editCart.observe(viewLifecycleOwner, Observer {response ->
        response.let { result ->
            result.onSuccess { data ->
                lifecycleScope.launch {
                    viewmodel.displayCartItems(sharedPreferencesHelper.getData()!!)
                }
            }
        }

        })
    }
}