package com.devamirali.foodapp.ui.fragment.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.FavoriteAdapter
import com.devamirali.foodapp.databinding.FragmentFavoriteBinding
import com.devamirali.foodapp.databinding.FragmentHomeBinding
import com.devamirali.foodapp.ui.activity.meal.MealActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val mealMvvm : MealActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSaveData()

//        binding.recFavorite.adapter = FavoriteAdapter()
//        binding.recFavorite.layoutManager = GridLayoutManager(requireContext(),2)
    }

    private fun getSaveData(){
        lifecycleScope.launch {
            mealMvvm.getSaveMeals().collect{savedData ->
                if (savedData.isEmpty()){
                    binding.txtWarning.visibility = View.VISIBLE
                    binding.txtTitle.visibility = View.GONE
                    binding.recFavorite.visibility = View.GONE
                }else{
                    Log.d("testApp", savedData.toString())
                    binding.recFavorite.adapter = FavoriteAdapter(savedData)
                    binding.recFavorite.layoutManager = GridLayoutManager(requireContext(),2)
                }

            }
        }
    }
}