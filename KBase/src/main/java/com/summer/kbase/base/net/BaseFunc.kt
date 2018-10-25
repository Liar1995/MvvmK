package com.summer.kbase.base.net

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.summer.kbase.common.LoggerUtils
import com.summer.kbase.utils.Utils.getJsonKey
import io.reactivex.Observable
import io.reactivex.functions.Function
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Created by sunmeng on 2018/3/14.
 * Email:sunmeng995@gmail.com
 * Description:接口响应处统一处理 网络请求返回处理 过滤状态码
 */

//普通接口转换
class BaseFunc<T> : Function<BaseResp<T>, Observable<T>> {

    /**
     * status 1：成功
     * status 0：失败
     */
    override fun apply(t: BaseResp<T>): Observable<T> {
        LoggerUtils.loggerD("BaseFunc 接口响应处统一处理 ： status : ${t.status}  message: ${t.message}")
        return if (t.status != ResultCode.SUCCESS_200) {
            Observable.error(BaseException(t.status, t.message))
        } else {
            Observable.just(t.data)
        }
    }
}

//restful api转换
class BaseFuncResponse<T> : Function<Response<T>, Observable<T>> {

    /**
     * status 1：成功
     * status 0：失败
     */
    override fun apply(t: Response<T>): Observable<T> {
        return when {
            t.code() == ResultCode.SUCCESS_200 -> Observable.just(t.body())
            else -> Observable.error(BaseException(t.code(), getJsonKey(t, "message")))
        }
    }
}