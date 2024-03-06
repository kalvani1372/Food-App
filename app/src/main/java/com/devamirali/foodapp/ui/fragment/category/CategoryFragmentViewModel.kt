package com.devamirali.foodapp.ui.fragment.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devamirali.foodapp.data.models.Over
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryFragmentViewModel
@Inject constructor(private val categoryRepository : CategoryFragmentRepository) : ViewModel() {

    private val _getCategoryStateFlow = MutableStateFlow<List<Over>>(emptyList())
    var getCategoryStateFlow : StateFlow<List<Over>> = _getCategoryStateFlow
    fun getCategory(categoryName : String){
        viewModelScope.launch {
            try {
                val response = categoryRepository.getCategory(categoryName)

                if (response.isSuccessful){
                 response.body()!!.meals.let {
                     _getCategoryStateFlow.emit(it)
                 }

                }
            }catch (t : Throwable){
                Log.d("testApp", t.message.toString())
            }
        }
    }
}