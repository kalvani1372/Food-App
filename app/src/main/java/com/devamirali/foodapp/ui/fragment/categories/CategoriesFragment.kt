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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.CategoriesAdapter
import com.devamirali.foodapp.databinding.FragmentCategoriesBinding
import com.devamirali.foodapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val categoriesMvvm: CategoriesFragmentViewModel by viewModels()
    private lateinit var owner: LifecycleOwner

    private lateinit var pageName : String
    private lateinit var countPage : String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.owner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCategories(view)
    }

    private fun getCategories(view: View) {
        categoriesMvvm.getCategories()
        categoriesMvvm.getCategoriesLiveData.observe(owner, Observer {
            binding.recCategories.adapter = CategoriesAdapter(it) {category ->

                val fragment = CategoriesFragment()
                val bundle = Bundle()
                pageName = "Go back to the categories page"
                countPage = "2"
                bundle.putString("pageName",pageName)
                bundle.putString("countPage",countPage)
                bundle.putString("strCategory",category.strCategory)
                fragment.arguments = bundle

                Navigation.findNavController(view)
                    .navigate(R.id.action_categoriesFragment_to_categoryFragment,fragment.arguments)
            }
            binding.recCategories.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        })
    }


}