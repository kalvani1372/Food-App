package com.devamirali.foodapp.ui.fragment.category

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.HomeCategoryAdapter
import com.devamirali.foodapp.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private val categoryMvvm: CategoryFragmentViewModel by viewModels()
    private lateinit var owner: LifecycleOwner

    private lateinit var categoryName: String
    private lateinit var pageName: String
    private lateinit var countPage: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.owner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments

        if (data != null) {
            categoryName = data.getString("strCategory").toString()
            pageName = data.getString("pageName").toString()
            countPage =data.getString("countPage").toString()

            categoryMvvm.getCategory(categoryName)
            categoryMvvm.getCategoryStateFlow.observe(owner, Observer {
                binding.txtPageName.text = pageName
                binding.recHomeCategory.adapter = HomeCategoryAdapter(it)
                binding.recHomeCategory.layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            })

        }

        binding.btnBack.setOnClickListener{
            if (countPage == "1"){
                Navigation.findNavController(view).navigate(R.id.action_categoryFragment_to_homeFragment)
            }else{
                Navigation.findNavController(view).navigate(R.id.action_categoryFragment_to_categoriesFragment)
            }
        }

        Log.d("testApp", categoryName)
    }
}