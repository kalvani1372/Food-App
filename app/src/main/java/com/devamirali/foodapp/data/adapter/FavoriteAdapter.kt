package com.devamirali.foodapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.models.Meal
import com.devamirali.foodapp.databinding.FavoriteRowBinding

class FavoriteAdapter(list : List<Meal>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteVH>() {

    private lateinit var binding: FavoriteRowBinding
    private val favoriteList = list
    class FavoriteVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVH {
        binding = FavoriteRowBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteVH(binding.root)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: FavoriteVH, position: Int) {
        val favoriteMeal = favoriteList[position]
        Glide.with(holder.itemView).load(favoriteMeal.strMealThumb).into(binding.favoriteImg)
        binding.txtFavoriteName.text = favoriteMeal.strMeal
    }
}