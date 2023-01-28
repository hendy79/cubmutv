package com.hendyapp.cubmutv.model

abstract class BaseResponse {
    abstract val status: Boolean
    abstract val statusCode: String
    abstract val option: String
    abstract val message: List<String>
    abstract val result: Any?
}