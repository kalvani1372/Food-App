package com.devamirali.foodapp.ui.fragment.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.CategoryAdapter
import com.devamirali.foodapp.data.adapter.OverAdapter
import com.devamirali.foodapp.databinding.FragmentHomeBinding
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
        getRandomMeal()
        getOverMeals()
        getCategoryMeals()
    }

    private fun getRandomMeal() {
        homeMvvm.getRandomMeal()
        homeMvvm.getRandomMealLiveData.observe(viewLifecycleOwner) {
            Glide.with(this).load(it.strMealThumb)
                .placeholder(R.drawable.food_logo).into(binding.randomImage)
        }
    }

    private fun getOverMeals() {

        homeMvvm.getOverMeals()
        homeMvvm.getOverMealLiveData.observe(viewLifecycleOwner) { data ->
            binding.overRec.adapter = OverAdapter(data)
            binding.overRec.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getCategoryMeals() {
        homeMvvm.getCategoryMeal()
        homeMvvm.getCategoryLiveData.observe(viewLifecycleOwner){
            binding.categoriesRec.adapter = CategoryAdapter(it)
            binding.categoriesRec.layoutManager = GridLayoutManager(requireContext(),3)
        }
    }

}