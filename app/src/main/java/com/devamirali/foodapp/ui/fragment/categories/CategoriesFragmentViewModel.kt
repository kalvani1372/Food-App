package com.devamirali.foodapp.ui.fragment.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devamirali.foodapp.data.models.Category
import com.devamirali.foodapp.data.models.CategoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesFragmentViewModel
@Inject constructor(private val categoriesRepository: CategoriesRepository) : ViewModel() {

    private val _getCategoriesLiveData = MutableLiveData<List<Category>>()
    var getCategoriesLiveData : LiveData<List<Category>> = _getCategoriesLiveData

    fun getCategories(){
        viewModelScope.launch {
            val response = categoriesRepository.getCategories()

            response.body()!!.categories.let {
                _getCategoriesLiveData.postValue(it)
            }
        }
    }

}