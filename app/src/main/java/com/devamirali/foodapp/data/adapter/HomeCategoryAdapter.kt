package com.devamirali.foodapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.models.Over
import com.devamirali.foodapp.databinding.HomeCategoryRowBinding

class HomeCategoryAdapter(list : List<Over>) :
    RecyclerView.Adapter<HomeCategoryAdapter.HomeCategoryVH>() {

    private val homeCategoryList = list
    private lateinit var binding: HomeCategoryRowBinding

    class HomeCategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryVH {
        binding = HomeCategoryRowBinding.inflate(LayoutInflater.from(parent.context))
        return HomeCategoryVH(binding.root)
    }

    override fun getItemCount(): Int {
        return homeCategoryList.size
    }

    override fun onBindViewHolder(holder: HomeCategoryVH, position: Int) {

        val homeCategory = homeCategoryList[position]

        Glide.with(holder.itemView).load(homeCategory.strMealThumb).into(binding.homeCategoryImg)
        binding.txtHomeCategoryName.text = homeCategory.strMeal
    }
}