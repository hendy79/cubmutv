package com.hendyapp.cubmutv.retrofit

import com.hendyapp.cubmutv.model.CategoriesResponse
import com.hendyapp.cubmutv.model.CouponsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiClients {
    @GET("getAllCategory")
    suspend fun getAllCategory(): Response<CategoriesResponse>

    @GET("getCoupon")
    suspend fun getCoupons(): Response<CouponsResponse>
}