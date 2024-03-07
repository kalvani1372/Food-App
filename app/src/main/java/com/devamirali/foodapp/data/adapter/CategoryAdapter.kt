package com.devamirali.foodapp.data.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.models.Category
import com.devamirali.foodapp.databinding.CategoryRowBinding
import com.devamirali.foodapp.ui.fragment.category.CategoryFragment

class CategoryAdapter(context: Activity, list : List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    private lateinit var binding : CategoryRowBinding
    private val iContext = context
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

            val fragment = CategoryFragment()
            val bundle = Bundle()
            bundle.putString("idCategory",category.idCategory)
            bundle.putString("strCategory",category.strCategory)
            bundle.putString("strCategoryThumb",category.strCategoryThumb)
            bundle.putString("strCategoryDescription",category.strCategoryDescription)
            fragment.arguments = bundle

           val intent = Intent(iContext,CategoryFragment::class.java)
            intent.putExtra("idCategory",category.idCategory)
            intent.putExtra("strCategory",category.strCategory)
            intent.putExtra("strCategoryThumb",category.strCategoryThumb)
            intent.putExtra("strCategoryDescription",category.strCategoryDescription)
            iContext.startActivity(intent)

        }
    }

}