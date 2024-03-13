package com.devamirali.foodapp.ui.fragment.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devamirali.foodapp.data.models.Over
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryFragmentViewModel
@Inject constructor(private val categoryRepository : CategoryFragmentRepository) : ViewModel() {

    private val _getCategoryStateFlow = MutableLiveData<List<Over>>()
    var getCategoryStateFlow : LiveData<List<Over>> = _getCategoryStateFlow
    fun getCategory(categoryName : String){
        viewModelScope.launch {
            try {
                val response = categoryRepository.getCategory(categoryName)

                if (response.isSuccessful){
                 response.body()!!.meals.let {
//                     _getCategoryStateFlow.emit(it)
                     _getCategoryStateFlow.postValue(it)
                 }

                }
            }catch (t : Throwable){
                Log.d("testApp", t.message.toString())
            }
        }
    }
}