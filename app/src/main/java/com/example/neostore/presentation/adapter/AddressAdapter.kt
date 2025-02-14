package com.example.neostore.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.interfaces.OnClickDoAction
import com.example.neostore.presentation.view.Address

class AddressAdapter(private val addressList: List<Address>, val onClickDoAction: OnClickDoAction): RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    class AddressViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val radioButton = itemView.findViewById<RadioButton>(R.id.rbAddress)
        val clearSelection = itemView.findViewById<ImageButton>(R.id.btnClear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.address_adapter, parent, false)
        return AddressViewHolder(view)
    }

    override fun getItemCount(): Int  = addressList.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val addressData = addressList[position]
        holder.radioButton.text = addressData.address

        holder.clearSelection.setOnClickListener{
            onClickDoAction.onClickGetDetails(addressData.id)
        }
    }
}