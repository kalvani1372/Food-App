package com.devamirali.foodapp.data.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.models.Category
import com.devamirali.foodapp.databinding.CategoriesRowBinding
import com.devamirali.foodapp.databinding.CategoryRowBinding

class CategoriesAdapter(list : List<Category>,private val listener : (Category) -> Unit)
    : RecyclerView.Adapter<CategoriesAdapter.CategoryVH>() {

    private lateinit var binding : CategoriesRowBinding
    private val categoryList = list
    class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        binding = CategoriesRowBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryVH(binding.root)
    }

    override fun getItemCount(): Int {
        return categoryList.size - 2
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val category = categoryList[position]

        Glide.with(holder.itemView).load(category.strCategoryThumb).into(binding.categoryImg)
        binding.txtCategory.text = "Name : ${category.strCategory}"
        binding.txtDesCategory.text = "Description : ${category.strCategoryDescription}"

        binding.cardView.setOnClickListener{
            listener.invoke(category)
        }

    }

}