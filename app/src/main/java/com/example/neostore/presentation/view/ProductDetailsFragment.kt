package com.example.neostore.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.neostore.R
import com.example.neostore.databinding.FragmentProductDetailsBinding
import com.example.neostore.databinding.FragmentTablesBinding
import com.example.neostore.domain.model.ProductDetailsRequest
import com.example.neostore.domain.model.ProductImage
import com.example.neostore.domain.model.ProductRequest
import com.example.neostore.presentation.adapter.ProductImageAdapter
import com.example.neostore.presentation.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loader = CustomLoader(requireContext())

        val id = arguments?.getInt("id")
        Log.d("prodId", "${id}")
        val tableHeading = binding.tvTablesHeading
        var prodImg = binding.ivProdMainImg
//        val category = binding.tvCategory
        val description = binding.tvDecription
        val producer = binding.tableProducer
        val price = binding.tvPrice

        val productDetailsRequest = ProductDetailsRequest(id.toString())
        viewModel.getProductDetails(productDetailsRequest)
        loader.showLoader()

        viewModel.productDetails.observe(viewLifecycleOwner, Observer { response ->
            response.let {result ->
                result.onSuccess { data ->
                    loader.dismiss()
                    tableHeading.text = data.data.name
//                    prodImg = data.data.product_images[id]
                    Log.d("ImageURL", "Image URL: ${data.data.product_images}")

                    Glide.with(binding.ivProdMainImg.context)
                        .load(data.data.product_images[0].image)
                        .into(prodImg)

                    description.text = data.data.description
                    producer.text = data.data.producer
                    price.text = data.data.cost.toString()

                    val productList = mutableListOf<String>()
                    for(i in 0 until data.data.product_images.size){
                        productList.add(data.data.product_images[i].image)
                    }
                    binding.rvProductImgs.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvProductImgs.adapter = ProductImageAdapter(productList)
                }
            }
        })
    }
}