package com.devamirali.foodapp.ui.fragment.home

import android.util.Log
import com.devamirali.foodapp.data.api.MealApi
import com.devamirali.foodapp.data.models.CategoryList
import com.devamirali.foodapp.data.models.MealList
import com.devamirali.foodapp.data.models.OverList
import retrofit2.Response
import javax.inject.Inject

class HomeRepository
@Inject constructor(private val mealApi: MealApi){

    suspend fun getRandomMeal() : Response<MealList>{
        val responseRandomMeal = mealApi.getRandomMeal()

        if (responseRandomMeal.isSuccessful){
            Log.d("testApp", responseRandomMeal.code().toString())
            Log.d("testApp", "Success to connected to Random meals")
        }else{
            Log.d("testApp", responseRandomMeal.code().toString())
            Log.d("testApp", "Failed to connected to Random meals")
        }
        return responseRandomMeal
    }

    suspend fun getOverMeal(categoryName : String) : Response<OverList>{
        val responseOverMeal = mealApi.getOverMeal(categoryName)

        if (responseOverMeal.isSuccessful){
            Log.d("testApp", responseOverMeal.code().toString())
            Log.d("testApp", "Success to connected to over meals")
        }else{
            Log.d("testApp", responseOverMeal.code().toString())
            Log.d("testApp", "Failed to connected to over meals")
        }

        return responseOverMeal
    }

    suspend fun getCategory() : Response<CategoryList>{
        val responseCategory = mealApi.getCategory()

        if (responseCategory.isSuccessful){
            Log.d("testApp", responseCategory.code().toString())
            Log.d("testApp", "Success to connected to Category meals")
        }else{
            Log.d("testApp", responseCategory.code().toString())
            Log.d("testApp", "Failed to connected to Category meals")
        }
        return responseCategory
    }

}