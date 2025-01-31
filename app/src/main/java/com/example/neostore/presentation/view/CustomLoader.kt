package com.example.neostore.presentation.view

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.example.neostore.R

class CustomLoader(private val context: Context) {
    private var dialog: Dialog? = null

    fun showLoader() {
        dialog = Dialog(context).apply {
            setContentView(LayoutInflater.from(context).inflate(R.layout.progress_loader, null))
            setCancelable(true)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            show()
        }
    }
    fun dismiss(){
        dialog?.dismiss()
    }
}