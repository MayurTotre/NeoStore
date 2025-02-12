package com.example.neostore.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neostore.R
import com.example.neostore.domain.model.CartData
import com.example.neostore.interfaces.OnClickAddOrRemove
import com.example.neostore.interfaces.OnClickDoAction

class DisplayCartAdapter(private val cartProducts: List<CartData>, private val onClick: OnClickDoAction,private val onClickAddOrRemove: OnClickAddOrRemove ): RecyclerView.Adapter<DisplayCartAdapter.DisplayCartViewHolder>() {
    class DisplayCartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cartCard = itemView.findViewById<CardView>(R.id.cvCartItem)
        val productCategory = itemView.findViewById<TextView>(R.id.tvCartProductCategory)
        val productQuantity = itemView.findViewById<TextView>(R.id.tvCartProductQuantity)
        val productPrice = itemView.findViewById<TextView>(R.id.tvCartProductPrice)
        val productTitle = itemView.findViewById<TextView>(R.id.tvCartProductTitle)
        val productImage = itemView.findViewById<ImageView>(R.id.ivCartProductImage)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)
        val btnAdd = itemView.findViewById<ImageButton>(R.id.btnAdd)
        val btnRemove = itemView.findViewById<ImageButton>(R.id.btnRemove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayCartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_cart_adapter, parent, false)
        return DisplayCartViewHolder(view)
    }

    override fun getItemCount(): Int = cartProducts.size

    override fun onBindViewHolder(holder: DisplayCartViewHolder, position: Int) {
        val cartProducts = cartProducts[position]
        val imageUrl = cartProducts.product.product_images
        Glide.with(holder.productImage.context)
            .load(imageUrl)
            .into(holder.productImage)
        holder.productTitle.text = cartProducts.product.name
        holder.productCategory.text = " Category:${cartProducts.product.product_category.toString()}"
        holder.productQuantity.text = "${cartProducts.quantity.toString()}"
        holder.productPrice.text = "Rs. ${cartProducts.product.cost.toString()}"

        holder.btnDelete.setOnClickListener {
            onClick.onClickGetDetails(cartProducts.product_id)
        }

        holder.btnAdd.setOnClickListener{
            onClickAddOrRemove.onClickAddOrRemove(cartProducts.product_id, cartProducts.quantity + 1)
        }

        holder.btnRemove.setOnClickListener{
            onClickAddOrRemove.onClickAddOrRemove(cartProducts.product_id, cartProducts.quantity - 1)
        }
    }
}