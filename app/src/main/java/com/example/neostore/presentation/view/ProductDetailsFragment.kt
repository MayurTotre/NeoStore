package com.example.neostore.presentation.view

import android.app.AlertDialog
import android.content.SharedPreferences.Editor
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.neostore.interfaces.OnClickDoAction
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.neostore.R
import com.example.neostore.databinding.FragmentProductDetailsBinding
import com.example.neostore.domain.model.AddToCartRequest
import com.example.neostore.domain.model.ProductDetailsRequest
import com.example.neostore.domain.model.ProductRatingRequest
import com.example.neostore.presentation.adapter.ProductImageAdapter
import com.example.neostore.presentation.viewmodels.AddToCartViewModel
import com.example.neostore.presentation.viewmodels.ProductsViewModel
import com.example.neostore.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(), OnClickDoAction {
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel: ProductsViewModel by viewModels()
    private val addToCartViewModel: AddToCartViewModel by viewModels()
    val productList = mutableListOf<String>()


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

        val sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        val accessToken =  sharedPreferencesHelper.getData()

        val id = arguments?.getInt("id")
        Log.d("prodId", "${id}")
        val tableHeading = binding.tvTablesHeading
        var prodImg = binding.ivProdMainImg
//        val category = binding.tvCategory
        val description = binding.tvDecription
        val producer = binding.tableProducer
        val price = binding.tvPrice
        val rating = binding.ratingProd
        val productDetailsRequest = ProductDetailsRequest(id.toString())

        viewModel.getProductDetails(productDetailsRequest)
        loader.showLoader()

        val ratingDilogueView = layoutInflater.inflate(R.layout.set_rating_dialogue, null)
        val buyNowDialogueView = layoutInflater.inflate(R.layout.addtocart_dialogue, null)

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
                    rating.rating = data.data.rating.toFloat()

                    for(i in 0 until data.data.product_images.size){
                        productList.add(data.data.product_images[i].image)
                    }
                    binding.rvProductImgs.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvProductImgs.adapter = ProductImageAdapter(productList,this)

                    ratingDilogueView.findViewById<TextView>(R.id.tvProductNameForRating).text = tableHeading.text.toString()
                    val image = ratingDilogueView.findViewById<ImageView>(R.id.ivProductImage)
                    Glide.with(image.context)
                        .load(data.data.product_images[0].image)
                        .into(image)

                    buyNowDialogueView.findViewById<TextView>(R.id.tvProductName).text = tableHeading.text.toString()
                    val imagebuynow = buyNowDialogueView.findViewById<ImageView>(R.id.ivProductImage)
                    Glide.with(imagebuynow.context)
                        .load(data.data.product_images[0].image)
                        .into(imagebuynow)
                }
            }
        })


        val ratingDialogue = AlertDialog.Builder(requireContext())
            .setView(ratingDilogueView)
            .setCancelable(true)
            .create()
        val btnRateNow = ratingDilogueView.findViewById<Button>(R.id.btnRateNow)

        binding.btnRate.setOnClickListener{
            ratingDialogue.show()
        }

        btnRateNow.setOnClickListener{
            val productRatingRequest = ProductRatingRequest(
                product_id = id.toString(),
                rating = ratingDialogue.findViewById<RatingBar>(R.id.rateNowBar)!!.rating.toInt()
                )
            viewModel.setProductRatring(productRatingRequest)

            viewModel.setProductRating.observe(viewLifecycleOwner, Observer { response ->
                response.let { result ->
                    result.onSuccess { data ->
                        Toast.makeText(requireContext(),  "Thankyou for Rating", Toast.LENGTH_SHORT).show()
                        ratingDialogue.dismiss()
                    }
                    result.onFailure {data ->
                        Toast.makeText(requireContext(),  "ERROR ${data.message}", Toast.LENGTH_SHORT).show()
                        ratingDialogue.dismiss()
                    }
                }
            })
        }

        val buyNowDialogue = AlertDialog.Builder(requireContext())
            .setView(buyNowDialogueView)
            .setCancelable(true)
            .create()
        val btnBuyNowDialog = buyNowDialogueView.findViewById<Button>(R.id.btnSubmit)

        binding.btnBuyNow.setOnClickListener{
            buyNowDialogue.show()
        }

        btnBuyNowDialog.setOnClickListener {
            val addToCartRequest = AddToCartRequest(
                product_id = id!!,
                quantity = buyNowDialogue.findViewById<EditText>(R.id.etquantity)?.text.toString().toInt())

            addToCartViewModel.addToCart(accessToken!!, addToCartRequest)

            addToCartViewModel.addToCart.observe(viewLifecycleOwner, Observer { response ->
                response.let { result ->
                    result.onSuccess { data ->
                        Toast.makeText(requireContext(),  "Added to cart", Toast.LENGTH_SHORT).show()
                        buyNowDialogue.dismiss()
                    }
                    result.onFailure {data ->
                        Toast.makeText(requireContext(),  "ERROR ${data.message}", Toast.LENGTH_SHORT).show()
                        buyNowDialogue.dismiss()
                    }
                }
            })
        }

    }

    override fun onClickGetDetails(id: Int) {
        Glide.with(binding.ivProdMainImg.context)
            .load(productList.get(id))
            .into(binding.ivProdMainImg)
    }

}