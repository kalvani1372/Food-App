package com.devamirali.foodapp.ui.fragment.categories

import android.util.Log
import com.devamirali.foodapp.data.models.CategoryList
import com.devamirali.foodapp.data.models.OverList
import com.devamirali.foodapp.data.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class CategoriesRepository
@Inject constructor(private val mealApi: MealApi) {

    suspend fun getCategories() : Response<CategoryList> {
        val responseCategories = mealApi.getCategories()

        if (responseCategories.isSuccessful){
            Log.d("testApp", responseCategories.code().toString())
            Log.d("testApp", "Success to connected to categories")
        }else{
            Log.d("testApp", responseCategories.code().toString())
            Log.d("testApp", "Failed to connected to categories")
        }
        return responseCategories
    }

}