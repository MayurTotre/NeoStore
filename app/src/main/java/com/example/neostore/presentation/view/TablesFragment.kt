package com.example.neostore.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostore.R
import com.example.neostore.databinding.FragmentTablesBinding
import com.example.neostore.domain.model.ProductRequest
import com.example.neostore.interfaces.OnClickDoAction
import com.example.neostore.presentation.adapter.TablesAdapter
import com.example.neostore.presentation.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class TablesFragment : Fragment(), OnClickDoAction{
    private lateinit var binding: FragmentTablesBinding
    private val viewModel: ProductsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTablesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loader = CustomLoader(requireContext())

        val id = arguments?.getInt("id").toString()
        val productRequest = ProductRequest(id)

        viewModel.getProducts(productRequest)
        loader.showLoader()

        viewModel.productList.observe(viewLifecycleOwner, Observer { response ->
            response.let { result ->
                result.onSuccess { data ->
                    loader.dismiss()
                    val productList = data.data

                    binding.rvTables.layoutManager = LinearLayoutManager(requireActivity())
                    binding.rvTables.adapter = TablesAdapter(productList, this)
                }
            }
        })

    }

    override fun onClickGetDetails(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        Log.d("prodId table", "${id}")

        val navController = findNavController()
        navController.navigate(R.id.action_tablesFragment_to_productDetailsFragment, bundle)
    }

}