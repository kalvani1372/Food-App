package com.devamirali.foodapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devamirali.foodapp.data.models.Meal

@Database(entities = [Meal::class], version = 3)
@TypeConverters(MealTypeConvertor::class)
abstract class MealDataBase : RoomDatabase(){
abstract fun mealDao() : MealDao

}