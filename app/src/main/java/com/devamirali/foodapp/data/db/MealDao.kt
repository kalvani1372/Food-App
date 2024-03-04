package com.devamirali.foodapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devamirali.foodapp.data.models.Meal
import kotlinx.coroutines.flow.Flow


@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: Meal) : Long
    @Delete
    suspend fun deleteMeal(meal: Meal)
    @Query("SELECT * FROM mealInformation")
    fun getSaveMeal() : Flow<List<Meal>>
}