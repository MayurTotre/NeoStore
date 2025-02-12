package com.example.neostore.presentation.view

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.neostore.R
import com.example.neostore.databinding.FragmentUserDataBinding
import com.example.neostore.interfaces.OnClickDoAction
import com.example.neostore.presentation.adapter.CategoriesAdapter
import com.example.neostore.presentation.adapter.ProductsAdapter
import com.example.neostore.presentation.viewmodels.RegistrationViewModel
import com.example.neostore.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDataFragment : Fragment(), OnClickDoAction{
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
        val loader = CustomLoader(requireContext())
        setupDrawer()

        val headerView = binding.navView.getHeaderView(0)
        val tvUserName = headerView.findViewById<TextView>(R.id.userName)
        val mail = headerView.findViewById<TextView>(R.id.tvMail)

        val accessToken = sharedPreferences.getData()
        Log.d("sharedPref", accessToken.toString())

        viewModel.getUserData(accessToken!!)
        loader.showLoader()

        viewModel.getUserData.observe(viewLifecycleOwner, Observer { response ->
            response.let { result ->
                result.onSuccess { data ->
                    loader.dismiss()
                    tvUserName.text = data.data.user_data.username
                    mail.text = data.data.user_data.email
                    val image = mutableListOf<String>()

                    val size = data.data.product_categories.size
                    for (i in 0 until size) {
                        image.add(data.data.product_categories[i].icon_image)
                    }

                    val productsAdapter = ProductsAdapter(image)
                    binding.vpProductsImages.adapter = productsAdapter

                    //val categoryImage = mutableListOf<ProductCategory>()

                    val category = data.data.product_categories

                    binding.rvCategories.layoutManager = GridLayoutManager(requireActivity(), 2)
                    val adapter = CategoriesAdapter(category, this)

                    binding.rvCategories.adapter = adapter
                    adapter.notifyDataSetChanged()


                }
            }
        })
    }

    private fun setupDrawer() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.mainScreenToolbar)


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

        binding.navView.setNavigationItemSelectedListener { menu ->
            when(menu.itemId){
                R.id.myCart ->{
                    findNavController().navigate(R.id.action_userDataFragment_to_displayCartItems)
                    true
                }else -> false
            }
        }

    }

    override fun onClickGetDetails(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val navController  = findNavController()
        navController.navigate(R.id.action_userDataFragment_to_tablesFragment, bundle)

    }
}
