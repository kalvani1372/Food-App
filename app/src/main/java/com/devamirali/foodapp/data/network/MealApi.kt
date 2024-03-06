package com.devamirali.foodapp.data.network

import com.devamirali.foodapp.data.models.CategoryList
import com.devamirali.foodapp.data.models.MealList
import com.devamirali.foodapp.data.models.OverList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal() : Response<MealList>

    @GET("filter.php")
    suspend fun getOverMeal(@Query("c") categoryName : String) : Response<OverList>

    @GET("categories.php")
    suspend fun getCategoryHomeFragment() : Response<CategoryList>

    @GET("lookup.php")
    suspend fun getMealInformation(@Query("i") mealId : String) : Response<MealList>

    @GET("filter.php")
    suspend fun getCategory(@Query("c") categoryName : String) : Response<OverList>
}