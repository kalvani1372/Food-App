package com.devamirali.foodapp.ui.activity.meal

import android.util.Log
import com.devamirali.foodapp.data.api.MealApi
import com.devamirali.foodapp.data.db.MealDataBase
import com.devamirali.foodapp.data.models.Meal
import com.devamirali.foodapp.data.models.MealList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MealRepository @Inject constructor(private val mealApi: MealApi,db : MealDataBase) {

    private val database = db.mealDao()
    val getMealSave = database.getSaveMeal()
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

    fun upsertMeal(meal: Meal){
        GlobalScope.launch {
            database.upsertMeal(meal)
        }
    }
    suspend fun deleteMeal(meal: Meal){
        database.deleteMeal(meal)
    }
}