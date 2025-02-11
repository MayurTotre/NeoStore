package com.example.neostore.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.neostore.interfaces.OnClickDoAction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neostore.R

class ProductImageAdapter(private val productImageList: List<String>, val onClickAction: OnClickDoAction): RecyclerView.Adapter<ProductImageAdapter.ProductImageViewHolder>() {
    class ProductImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productImage: ImageView = itemView.findViewById(R.id.ivProdImages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_image_adapter, parent, false)
        return ProductImageViewHolder(view)
    }

    override fun getItemCount(): Int = productImageList.size

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        val imageUrl = productImageList[position]

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.productImage)

        holder.productImage.setOnClickListener{
            onClickAction.onClickGetDetails(position)
        }

    }

}