package com.devamirali.foodapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.devamirali.foodapp.data.models.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert
    suspend fun insert(meal: Meal)
    @Delete
    suspend fun deleteMeal(meal: Meal)
    @Query("SELECT * FROM mealInformation")
    suspend fun getMeal() : Flow<Meal>
}