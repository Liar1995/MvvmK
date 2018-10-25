package com.summer.kbase.base.net

import android.content.Context
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import okhttp3.*
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.summer.kbase.BuildConfig


/**
 * Created by sunmeng on 2018/3/14.
 * Email:sunmeng995@gmail.com
 * Description:NetProvider实现
 */
class NetProviderImpl(private val mContext: Context) : NetProvider {

    companion object {
        const val CONNECT_TIME_OUT: Long = 20
        const val READ_TIME_OUT: Long = 180
        const val WRITE_TIME_OUT: Long = 30
    }

    override fun configInterceptors(): Array<Interceptor>? = null

    override fun configHttps(builder: OkHttpClient.Builder) {}

    override fun configCookie(): CookieJar? = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(mContext))

    override fun configHandler(): RequestHandler = HeaderHandler()

    override fun configConnectTimeoutSecs(): Long = CONNECT_TIME_OUT

    override fun configReadTimeoutSecs(): Long = READ_TIME_OUT

    override fun configWriteTimeoutSecs(): Long = WRITE_TIME_OUT

    override fun configLogEnable(): Boolean = BuildConfig.DEBUG

    //可在请求执行前和请求执行后做网络统一处理
    inner class HeaderHandler : RequestHandler {
        override fun onBeforeRequest(request: Request, chain: Interceptor.Chain): Request {
//            LoggerUtils.loggerE("可在请求执行前和请求执行后做网络统一处理 onBeforeRequest")
            return request
        }

        override fun onAfterRequest(response: Response, chain: Interceptor.Chain): Response {
//            LoggerUtils.loggerE("可在请求执行前和请求执行后做网络统一处理 onAfterRequest")
//            val json= String(response.body().bytes())
//            LoggerUtils.loggerJson(json)
            return response
        }
    }
}