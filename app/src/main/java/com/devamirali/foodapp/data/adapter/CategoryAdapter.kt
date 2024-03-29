package com.devamirali.foodapp.data.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.models.Category
import com.devamirali.foodapp.databinding.CategoryRowBinding

class CategoryAdapter(list : List<Category>, private var listiner: (Category) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    private lateinit var binding : CategoryRowBinding
    private val categoryList = list
    class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        binding = CategoryRowBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryVH(binding.root)
    }

    override fun getItemCount(): Int {
        return categoryList.size - 2
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val category = categoryList[position]

        Glide.with(holder.itemView).load(category.strCategoryThumb).into(binding.categoryImg)
        binding.txtCategory.text = category.strCategory

        binding.layHead.setOnClickListener {

            listiner.invoke(category)

        }
    }

}