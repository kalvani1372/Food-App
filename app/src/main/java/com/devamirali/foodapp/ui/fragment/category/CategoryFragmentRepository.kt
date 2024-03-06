package com.devamirali.foodapp.ui.fragment.category

import android.util.Log
import com.devamirali.foodapp.data.models.OverList
import com.devamirali.foodapp.data.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class CategoryFragmentRepository
@Inject constructor(private val mealApi: MealApi) {
    suspend fun getCategory(categoryName : String) : Response<OverList>{
        val response = mealApi.getCategory(categoryName)
        if (response.isSuccessful){
            Log.d("testApp", "Success to connected to category")
            Log.d("testApp", response.code().toString())
        }else{
            Log.d("testApp", "Failed to connected to category")
            Log.d("testApp", response.code().toString())
        }
        return response
    }
}