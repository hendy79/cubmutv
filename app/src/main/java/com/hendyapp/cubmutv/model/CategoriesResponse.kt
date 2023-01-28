package com.hendyapp.cubmutv.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("status")
    override val status: Boolean,
    @SerializedName("statusCode")
    override val statusCode: String,
    @SerializedName("option")
    override val option: String,
    @SerializedName("message")
    override val message: List<String>,
    @SerializedName("result")
    override val result: List<Category>?
): BaseResponse()


data class Category(
    @SerializedName("categoryId")
    val id: Int,
    @SerializedName("categoryName")
    val name: String,
    var coupons: List<Coupon>
)