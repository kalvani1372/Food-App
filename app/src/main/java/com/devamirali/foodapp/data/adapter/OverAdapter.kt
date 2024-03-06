package com.devamirali.foodapp.data.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.models.Over
import com.devamirali.foodapp.databinding.OverRowBinding
import com.devamirali.foodapp.ui.activity.meal.MealActivity

class OverAdapter(context: Context,list : List<Over>) : RecyclerView.Adapter<OverAdapter.OverVH>() {
    
    private lateinit var binding : OverRowBinding
    private val iContext = context
    private val overList = list
    
    class OverVH(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverVH {
        binding = OverRowBinding.inflate(LayoutInflater.from(parent.context))
        return OverVH(binding.root)
    }

    override fun getItemCount(): Int {
        return overList.size
    }

    override fun onBindViewHolder(holder: OverVH, position: Int) {
        val over = overList[position]
        Glide.with(binding.overImg).load(over.strMealThumb).into(binding.overImg)

        binding.overImg.setOnClickListener {
            val intent = Intent(iContext,MealActivity::class.java)
            intent.putExtra("idMeal",over.idMeal)
            intent.putExtra("strMealThumb",over.strMealThumb)
            intent.putExtra("strMeal",over.strMeal)
            iContext.startActivity(intent)
        }
    }

}