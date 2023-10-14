package com.thermondo.androidchallenge.api.response

import com.thermondo.androidchallenge.common.fromJson
import java.nio.charset.Charset

class ApiResponse<T : Any>(
    private val response: retrofit2.Response<T>
) {

    val data: T? by lazy {
        response.body()
    }

    val error: ErrorResponse? by lazy {
        try {
            val strResponse = response.errorBody()?.byteString()?.string(Charset.defaultCharset()) ?: return@lazy null
            fromJson<ErrorResponse>(strResponse)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun isSuccessful() = response.isSuccessful
}