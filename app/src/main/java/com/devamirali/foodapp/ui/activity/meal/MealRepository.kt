package com.devamirali.foodapp.ui.activity.meal

import android.util.Log
import com.devamirali.foodapp.data.api.MealApi
import com.devamirali.foodapp.data.models.MealList
import retrofit2.Response
import javax.inject.Inject

class MealRepository @Inject constructor(private val mealApi: MealApi) {

    suspend fun getMealInformation(mealId : String):Response<MealList>{
        val response = mealApi.getMealInformation(mealId)

        if (response.isSuccessful){
            Log.d("testApp", response.code().toString())
            Log.d("testApp", "Success to connected to getMealInformation")
        }else{
            Log.d("testApp", response.code().toString())
            Log.d("testApp", "Failure to connected to getMealInformation")
        }
        return response
    }
}