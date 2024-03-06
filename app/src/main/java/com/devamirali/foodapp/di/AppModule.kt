package com.devamirali.foodapp.di

import android.app.Application
import androidx.room.Room
import com.devamirali.foodapp.data.network.MealApi
import com.devamirali.foodapp.data.db.MealDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi() : MealApi = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(MealApi::class.java)

    @Provides
    @Singleton
    fun provideDataBase(app : Application) : MealDataBase =
        Room.databaseBuilder(app,MealDataBase::class.java,"meal.db").fallbackToDestructiveMigration().build()
}