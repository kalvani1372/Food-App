package com.devamirali.foodapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devamirali.foodapp.data.models.Category
import com.devamirali.foodapp.data.models.Meal
import com.devamirali.foodapp.data.models.Over
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
@HiltViewModel
class HomeViewModel
@Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _getRandomMealLiveData = MutableLiveData<Meal>()
    val getRandomMealLiveData: LiveData<Meal> = _getRandomMealLiveData

    fun getRandomMeal() {
        viewModelScope.launch {
            val response = homeRepository.getRandomMeal()
            response.body()!!.meals.let {
                _getRandomMealLiveData.postValue(it[0])
            }
        }
    }


    private val _getOverMealLiveData = MutableLiveData<List<Over>>()
    val getOverMealLiveData: LiveData<List<Over>> = _getOverMealLiveData

    fun getOverMeals() {
        viewModelScope.launch {
            val response = homeRepository.getOverMeal("seafood")
            response.body()!!.meals.let {
                _getOverMealLiveData.postValue(it)
            }
        }
    }

    private val _getCategoryLiveData = MutableLiveData<List<Category>>()
    val getCategoryLiveData : LiveData<List<Category>> = _getCategoryLiveData

    fun getCategoryMealHomeFragment(){
        viewModelScope.launch {
            val response = homeRepository.getCategoryHomeFragment()
            response.body()!!.categories.let {
                _getCategoryLiveData.postValue(it)
            }
        }
    }

}