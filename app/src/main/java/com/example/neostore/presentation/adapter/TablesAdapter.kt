package com.example.neostore.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neostore.R
import com.example.neostore.domain.model.ProductsData
import com.example.neostore.interfaces.Products

class TablesAdapter(private val productList: List<ProductsData>, val onClickAction: Products): RecyclerView.Adapter<TablesAdapter.TableViewHolder>() {
    class TableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tableImage = itemView.findViewById<ImageView>(R.id.ivTables)
        var tableHeading = itemView.findViewById<TextView>(R.id.tvTablesHeading)
        val tableProducer = itemView.findViewById<TextView>(R.id.tableProducer)
        val tablePrice = itemView.findViewById<TextView>(R.id.tablePrice)
        val productCard = itemView.findViewById<CardView>(R.id.cvProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tables_adapter, parent, false)
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val productData = productList[position]
        val imageUrl = productData.product_images
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.tableImage)

        holder.tableHeading.text = productData.name
        holder.tableProducer.text = productData.producer
        holder.tablePrice.text = productData.cost.toString()

        holder.productCard.setOnClickListener {

            Log.d("prodId adapter", "${productData.id}")

            onClickAction.onClickGetDetails(productData.id)
        }

    }
}