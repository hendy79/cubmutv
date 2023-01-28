package com.hendyapp.cubmutv.view.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hendyapp.cubmutv.model.Category
import com.hendyapp.cubmutv.repo.GlobalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: GlobalRepository
): ViewModel() {
    val categoriesLiveData = MutableLiveData<List<Category>>()

    fun getCategoriesWithCoupons() = viewModelScope.launch {
        val categories = repo.getAllCategory()
        val coupons = repo.getCoupons()
        if(categories?.result != null && coupons?.result != null) {
            categories.result.forEach { category ->
                category.coupons = coupons.result.shuffled()
            }
            categoriesLiveData.postValue(categories.result!!)
        }
    }
}