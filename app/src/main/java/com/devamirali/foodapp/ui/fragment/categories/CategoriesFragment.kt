package com.devamirali.foodapp.ui.fragment.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devamirali.foodapp.R
import com.devamirali.foodapp.databinding.FragmentCategoriesBinding
import com.devamirali.foodapp.databinding.FragmentFavoriteBinding

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}