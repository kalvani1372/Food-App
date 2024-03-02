package com.devamirali.foodapp.ui.activity.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devamirali.foodapp.data.models.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealActivityViewModel
@Inject constructor(private val mealRepository: MealRepository) : ViewModel(){

    private val _getMealInformationLiveData = MutableLiveData<Meal>()
    val getMealInformationLiveData : LiveData<Meal> = _getMealInformationLiveData

    fun getMealInformation(mealId : String){
        viewModelScope.launch {
            val response = mealRepository.getMealInformation(mealId)
            if (response.isSuccessful){
                _getMealInformationLiveData.value = response.body()!!.meals[0]
//            response.body()!!.meals.let {data ->
//                _getMealInformationLiveData.postValue(data[0])
//            }
            }
        }
    }
}