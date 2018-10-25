package com.summer.kbase.base.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Created by sunmeng on 2018/3/14.
 * Email:sunmeng995@gmail.com
 * Description:OkHttpClient网络拦截器
 */
class NetInterceptor(private val handler: RequestHandler?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request().newBuilder()
                .addHeader("source", "Android")
                .build()
        if (handler != null) {
            request = handler.onBeforeRequest(request, chain)
        }
        val response = chain.proceed(request)
        if (handler != null) {
            return handler.onAfterRequest(response, chain)
        }
        return response
    }

}

/**
 * Created by sunmeng on 2018/3/14.
 * Email:sunmeng995@gmail.com
 * Description:OkHttpClient网络拦截层抽象
 */
interface RequestHandler {
    fun onBeforeRequest(request: Request, chain: Interceptor.Chain): Request

    @Throws(IOException::class)
    fun onAfterRequest(response: Response, chain: Interceptor.Chain): Response
}