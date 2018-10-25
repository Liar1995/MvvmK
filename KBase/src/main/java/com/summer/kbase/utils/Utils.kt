package com.summer.kbase.utils

import com.google.gson.JsonParser
import retrofit2.Response

/**
 * Created by sunmeng on 2018/10/25.
 * Email:sunmeng995@gmail.com
 * Description:
 */
object Utils {

    fun <T> getJsonKey(t: Response<T>, key: String): String {
        val json = t.errorBody()!!.string()
        val data = JsonParser().parse(json).asJsonObject
        return data.get(key).toString().replace("\"", "")
    }

}