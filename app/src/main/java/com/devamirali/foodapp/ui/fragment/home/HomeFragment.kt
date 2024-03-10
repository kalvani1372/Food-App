package com.devamirali.foodapp.ui.fragment.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.CategoryAdapter
import com.devamirali.foodapp.data.adapter.OverAdapter
import com.devamirali.foodapp.databinding.FragmentHomeBinding
import com.devamirali.foodapp.ui.activity.meal.MealActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeMvvm: HomeViewModel by viewModels()
    private lateinit var owner: LifecycleOwner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.owner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipe.setOnRefreshListener {
            getRandomMeal()
            binding.swipe.isRefreshing = false
        }
        getRandomMeal()
        getOverMeals()
        getCategoryMealsHomeFragment(binding.root)
    }

    private fun getRandomMeal() {
        homeMvvm.getRandomMeal()
        homeMvvm.getRandomMealLiveData.observe(viewLifecycleOwner) { data ->
            Glide.with(this).load(data.strMealThumb)
                .placeholder(R.drawable.food_logo).into(binding.randomImage)
            data.strCategory

            try {
                binding.randomImage.setOnClickListener{
                    val intent = Intent(requireContext(), MealActivity::class.java)
                    intent.putExtra("idMeal",data.idMeal)
                    intent.putExtra("strMeal",data.strMeal)
                    intent.putExtra("strMealThumb",data.strMealThumb)
                    intent.putExtra("strCategory",data.strCategory)
                    intent.putExtra("strArea",data.strArea)
                    startActivity(intent)
                }
            }catch (t:Throwable){
                Log.d("TAG", "getRandomMeal: ${t.message.toString()}")
            }
        }
    }

    private fun getOverMeals() {

        homeMvvm.getOverMeals()
        homeMvvm.getOverMealLiveData.observe(viewLifecycleOwner) { data ->
            binding.overRec.adapter = OverAdapter(requireContext(),data)
            binding.overRec.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getCategoryMealsHomeFragment(view: View) {
        homeMvvm.getCategoryMealHomeFragment()
        homeMvvm.getCategoryLiveData.observe(viewLifecycleOwner){
            binding.categoriesRec.adapter = CategoryAdapter(it) { category ->
                val bundle = Bundle()
                bundle.putString("idCategory", category.idCategory)
                bundle.putString("strCategory", category.strCategory)
                bundle.putString("strCategoryThumb", category.strCategoryThumb)
                bundle.putString("strCategoryDescription", category.strCategoryDescription)

                Navigation.findNavController(view)
                    .navigate(R.id.action_homeFragment_to_categoryFragment)


            }
            binding.categoriesRec.layoutManager = GridLayoutManager(requireContext(),3)
        }
    }

}