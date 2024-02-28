package com.devamirali.foodapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devamirali.foodapp.R
import com.devamirali.foodapp.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}