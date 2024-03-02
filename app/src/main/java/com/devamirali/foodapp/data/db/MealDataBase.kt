package com.devamirali.foodapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devamirali.foodapp.data.models.Meal

@Database(entities = [Meal::class], version = 1)
abstract class MealDataBase : RoomDatabase(){
abstract fun mealDao() : MealDao

}