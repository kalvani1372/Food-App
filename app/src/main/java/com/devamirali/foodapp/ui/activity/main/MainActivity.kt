package com.devamirali.foodapp.ui.activity.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.PagerAdapter
import com.devamirali.foodapp.databinding.ActivityMainBinding
import com.devamirali.foodapp.ui.fragment.categories.CategoriesFragment
import com.devamirali.foodapp.ui.fragment.favorite.FavoriteFragment
import com.devamirali.foodapp.ui.fragment.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("NewApi", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listFrag = mutableListOf<Fragment>()
        listFrag.add(HomeFragment())
        listFrag.add(FavoriteFragment())
        listFrag.add(CategoriesFragment())

        binding.pager.adapter = PagerAdapter(listFrag,this@MainActivity)
        binding.pager.isUserInputEnabled = false

        binding.btmNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home_frag -> {
                    binding.pager.currentItem = 0
                    true
                }

                R.id.item_favorite_frag -> {
                    binding.pager.currentItem = 1
                    true
                }

                R.id.item_categories_frag -> {
                    binding.pager.currentItem = 2
                    true
                }

                else -> false
            }
        }
    }
}