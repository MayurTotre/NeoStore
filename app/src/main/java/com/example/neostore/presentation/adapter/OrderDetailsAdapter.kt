package com.example.neostore.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.domain.model.OrderDetailsResponse

class OrderDetailsAdapter(private val orderList: List<OrderDetailsResponse>): RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder>(){
    class OrderDetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val orderId = itemView.findViewById<TextView>(R.id.tvOrderId)
        val price = itemView.findViewById<TextView>(R.id.tvOrderedItemPrice)
        val orderDate = itemView.findViewById<TextView>(R.id.tvOrderDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_details_adapter, parent, false)
        return OrderDetailsViewHolder(view)
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) {
        }
}