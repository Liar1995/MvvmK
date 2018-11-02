package com.summer.kbase.base.net.okhttp

import com.summer.kbase.base.net.okhttp.RequestHandler
import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * Created by sunmeng on 2018/3/14.
 * Email:sunmeng995@gmail.com
 * Description:网络配置
 */
interface NetProvider {

    fun configInterceptors(): Array<Interceptor>?

    fun configHttps(builder: OkHttpClient.Builder)

    fun configCookie(): CookieJar?

    fun configHandler(): RequestHandler

    fun configConnectTimeoutSecs(): Long

    fun configReadTimeoutSecs(): Long

    fun configWriteTimeoutSecs(): Long

    fun configLogEnable(): Boolean

}