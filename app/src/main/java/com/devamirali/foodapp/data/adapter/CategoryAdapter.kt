package com.devamirali.foodapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.models.Category
import com.devamirali.foodapp.databinding.CategoryRowBinding

class CategoryAdapter(list : List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    private lateinit var binding : CategoryRowBinding
    private val categoryList = list

    class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        binding = CategoryRowBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryVH(binding.root)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val category = categoryList[position]
        Glide.with(binding.categoryImg).load(category.strCategoryThumb).into(binding.categoryImg)
    }

}