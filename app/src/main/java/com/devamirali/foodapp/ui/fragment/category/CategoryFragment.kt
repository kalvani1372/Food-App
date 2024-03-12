package com.devamirali.foodapp.ui.fragment.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.adapter.HomeCategoryAdapter
import com.devamirali.foodapp.data.models.Over
import com.devamirali.foodapp.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding
    private lateinit var categoryName : String
    private lateinit var categoryId : String
    private lateinit var categoryNameThumb : String
    private lateinit var categoryNameDescription : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments

        if (data != null){
            categoryId = data.getString("idCategory").toString()
            categoryName = data.getString("strCategory").toString()
            categoryNameThumb = data.getString("strCategoryThumb").toString()
            categoryNameDescription = data.getString("strCategoryDescription").toString()

            val list = mutableListOf<String>()
            list.add(categoryName)
            list.add(categoryNameThumb)

            binding.recHomeCategory.adapter = HomeCategoryAdapter(list)
        }



        Log.d("testApp", categoryName)
    }
}