package com.handen.vtb_counterparts.network

import retrofit2.Response

abstract class BaseService {
    fun <ResponseType> makeCall(response: Response<ResponseType>): ResponseType {
        if (response.isSuccessful && response.body() != null) {
            return response.body() ?: throw IllegalStateException("response body is null")
        }
        println(response.errorBody())
        throw IllegalArgumentException()
    }
}