package com.hendyapp.cubmutv.repo

import com.hendyapp.cubmutv.model.CategoriesResponse
import com.hendyapp.cubmutv.model.CouponsResponse
import com.hendyapp.cubmutv.retrofit.ApiClients

class GlobalRepository(
    private val apiService: ApiClients,
) {
    suspend fun getAllCategory(): CategoriesResponse? {
        val response = apiService.getAllCategory()
        if(response.isSuccessful)
            return response.body()
        return null
    }

    suspend fun getCoupons(): CouponsResponse? {
        val response = apiService.getCoupons()
        if(response.isSuccessful)
            return response.body()
        return null
    }
}