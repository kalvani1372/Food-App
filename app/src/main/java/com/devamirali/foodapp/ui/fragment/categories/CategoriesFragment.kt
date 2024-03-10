package com.devamirali.foodapp.ui.fragment.categories

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.CategoriesAdapter
import com.devamirali.foodapp.databinding.FragmentCategoriesBinding
import com.devamirali.foodapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val categoriesMvvm : CategoriesFragmentViewModel by viewModels()
    private lateinit var owner: LifecycleOwner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.owner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCategories()
    }

    fun getCategories(){
        categoriesMvvm.getCategories()
        categoriesMvvm.getCategoriesLiveData.observe(owner, Observer {
            binding.recCategories.adapter = CategoriesAdapter(it)
            binding.recCategories.layoutManager = GridLayoutManager(requireActivity(),4)
        })
    }

}