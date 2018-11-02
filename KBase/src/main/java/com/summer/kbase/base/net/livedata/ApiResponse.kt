package com.summer.kbase.base.net.livedata

import android.support.v4.util.ArrayMap

import java.io.IOException
import java.util.regex.Pattern

import retrofit2.Response

/**
 * An ApiResponse represents a response returned by a web service.
 * ApiResponse allows the conversion of Retrofit2.Call into a LiveData.
 * */
class ApiResponse<T> {
    val code: Int
    val body: T?
    val errorMessage: String?

    val isSuccessful: Boolean
        get() = code in 200..299

    constructor(error: Throwable) {
        code = 500
        body = null
        errorMessage = error.message
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
    }
}

