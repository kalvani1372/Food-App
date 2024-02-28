package com.devamirali.foodapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.models.Over
import com.devamirali.foodapp.databinding.OverRowBinding

class OverAdapter(list : List<Over>) : RecyclerView.Adapter<OverAdapter.OverVH>() {
    
    private lateinit var binding : OverRowBinding
    private val overList = list
    
//    private val diffUtil = object  : DiffUtil.ItemCallback<Over>(){
//        override fun areItemsTheSame(oldItem: Over, newItem: Over): Boolean {
//            return oldItem.idMeal == newItem.idMeal
//        }
//
//        override fun areContentsTheSame(oldItem: Over, newItem: Over): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//
//    val differ = AsyncListDiffer(this,diffUtil)
    
    
    
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
    }

}