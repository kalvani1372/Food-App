package com.devamirali.foodapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devamirali.foodapp.data.models.CountryModel
import com.devamirali.foodapp.databinding.CountryRowBinding

class CountryAdapter(list : List<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryVH>() {

    private val countryList = list
    private lateinit var binding : CountryRowBinding
    class CountryVH(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryVH {
        binding = CountryRowBinding.inflate(LayoutInflater.from(parent.context))
        return CountryVH(binding.root)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryVH, position: Int) {
        val country = countryList[position]
        binding.txt1.text = country.countryName
    }
}