package com.devamirali.foodapp.ui.activity.meal

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.devamirali.foodapp.data.models.Meal
import com.devamirali.foodapp.databinding.ActivityMealBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    private lateinit var idMeal : String
    private lateinit var strMeal : String
    private lateinit var strMealThumb : String
    private lateinit var strCategory : String
    private lateinit var strArea : String
    private lateinit var youtube : String

    private val mealMvvm : MealActivityViewModel by viewModels()
    private lateinit var saveMeal : Meal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMealInformation()
        mealMvvm.getMealInformation(idMeal)
        observeGetMealInformation()
        binding.btnFav.post {

        binding.btnFav.setOnClickListener {
            saveMeal.let {meal ->
                    mealMvvm.upsertMeal(meal)
            }
        }

//            lifecycleScope.launch {
//                mealMvvm.getSaveMeals().collect {saveMeal ->
//                    Log.d("testApp", saveMeal.toString())
//
//                }
//            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getMealInformation(){
        val intent = intent
        idMeal = intent.getStringExtra("idMeal").toString()
        strMeal = intent.getStringExtra("strMeal").toString()
        strMealThumb = intent.getStringExtra("strMealThumb").toString()
        strCategory = intent.getStringExtra("strCategory").toString()
        strArea = intent.getStringExtra("strArea").toString()

        Log.d("idMeal", "idMeal: $idMeal")

        Glide.with(this).load(strMealThumb).into(binding.mealImage)
        binding.collapsing.title = strMeal
//        binding.txtCategoryName.text = "Category : $strCategory"
//        binding.txtArea.text = "Area : $strArea"

    }

    @SuppressLint("SetTextI18n")
    private fun observeGetMealInformation(){
        mealMvvm.getMealInformationLiveData.observe(this,Observer{data ->
            saveMeal = data
            binding.txtCategoryName.text = "Category : ${data.strCategory}"
            binding.txtArea.text = "Area : ${data.strArea}"
            binding.detailsInstructions.text = data.strInstructions

            youtube = data.strYoutube
            binding.btnYoutube.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtube))
                startActivity(intent)
            }
        })
    }
}