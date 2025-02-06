package com.example.neostore.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neostore.R
import com.example.neostore.domain.model.ProductCategory
import org.w3c.dom.Text

class CategoriesAdapter(private val categories: List<ProductCategory>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    class CategoriesViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryCard = itemView.findViewById<CardView>(R.id.cvCategory)
        var categoryText = itemView.findViewById<TextView>(R.id.tvCategory)
        val categoryImage = itemView.findViewById<ImageView>(R.id.ivCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_categories_adapter, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
//        val imageUrl = categories[position].icon_image
//        Glide.with(holder.itemView.context)
//            .load(imageUrl)
//            .into(holder.categoryImage)

        holder.categoryText.text = categories[position].name


    }
}