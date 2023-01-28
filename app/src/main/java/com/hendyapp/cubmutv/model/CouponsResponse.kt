package com.hendyapp.cubmutv.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CouponsResponse(
    @SerializedName("status")
    override val status: Boolean,
    @SerializedName("statusCode")
    override val statusCode: String,
    @SerializedName("option")
    override val option: String,
    @SerializedName("message")
    override val message: List<String>,
    @SerializedName("result")
    override val result: List<Coupon>?
): BaseResponse()

@Parcelize
data class Coupon(
    @SerializedName("couponId")
    val id: Int,
    @SerializedName("couponName")
    val name: String,
    @SerializedName("couponBrandName")
    val brandName: String,
    @SerializedName("couponBrandLogo")
    val brandLogo: String,
    @SerializedName("couponTnc")
    val couponTnc: String,
    var onHovered: Boolean = false
): Parcelable