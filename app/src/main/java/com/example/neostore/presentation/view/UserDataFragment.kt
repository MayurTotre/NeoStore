package com.example.neostore.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.neostore.R
import com.example.neostore.databinding.FragmentUserDataBinding
import com.example.neostore.presentation.adapter.ProductsAdapter
import com.example.neostore.presentation.viewmodels.RegistrationViewModel
import com.example.neostore.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDataFragment : Fragment() {
    private lateinit var binding: FragmentUserDataBinding
    private lateinit var toggle: ActionBarDrawerToggle
    val viewModel: RegistrationViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDataBinding.inflate(inflater, container, false)
        sharedPreferences = SharedPreferencesHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDrawer()

        val headerView = binding.navView.getHeaderView(0)
        val tvUserName = headerView.findViewById<TextView>(R.id.userName)
        val mail = headerView.findViewById<TextView>(R.id.tvMail)

    }

    private fun setupDrawer() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.mainScreenToolbar)

        val loader = CustomLoader(requireContext())

        toggle = ActionBarDrawerToggle(
            activity,
            binding.drawerLayout,
            binding.mainScreenToolbar,
            R.string.open,
            R.string.close
        )

        toggle.drawerArrowDrawable.color = ContextCompat.getColor(requireContext(), R.color.black)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val accessToken = sharedPreferences.getData()
        Log.d("sharedPref", accessToken.toString())

        viewModel.getUserData(accessToken!!)
        loader.showLoader()
        viewModel.getUserData.observe(viewLifecycleOwner, Observer { response ->
            response.let { result ->
                result.onSuccess { data ->
                    loader.dismiss()
                    val image = mutableListOf<String>()

                    val size = data.data.product_categories.size
                    for (i in 0 until size) {
                        image.add(data.data.product_categories[i].icon_image)
                    }

                    val productsAdapter = ProductsAdapter(image)
                    binding.vpProductsImages.adapter = productsAdapter
                }
            }
        })
    }
}
