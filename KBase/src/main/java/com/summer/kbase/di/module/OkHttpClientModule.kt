package com.summer.kbase.di.module

import android.app.Application
import com.summer.kbase.BuildConfig
import com.summer.kbase.base.BaseContract
import com.summer.kbase.base.net.okhttp.NetInterceptor
import com.summer.kbase.base.net.okhttp.NetProvider
import com.summer.kbase.base.net.okhttp.NetProviderImpl
import com.summer.kbase.base.net.logging.Level
import com.summer.kbase.base.net.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by sunmeng on 2018/10/23.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module
class OkHttpClientModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache, context: Application): OkHttpClient {
        val provider: NetProvider = NetProviderImpl(context)
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(if (provider.configConnectTimeoutSecs() != 0L)
            provider.configConnectTimeoutSecs()
        else
            BaseContract.CONNECTTIMEOUTMILLS, TimeUnit.SECONDS)

        builder.readTimeout(if (provider.configReadTimeoutSecs() != 0L)
            provider.configReadTimeoutSecs()
        else
            BaseContract.READTIMEOUTMILLS, TimeUnit.SECONDS)

        builder.writeTimeout(if (provider.configWriteTimeoutSecs() != 0L)
            provider.configReadTimeoutSecs()
        else
            BaseContract.READTIMEOUTMILLS, TimeUnit.SECONDS)

        builder.cache(cache)

        val cookieJar = provider.configCookie()
        if (cookieJar != null) {
            builder.cookieJar(cookieJar)
        }
        provider.configHttps(builder)

        builder.addInterceptor(NetInterceptor(provider.configHandler()))

        if (provider.configLogEnable()) {
            builder.addInterceptor(LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request")
                    .response("Response")
                    .addHeader("header", "context")
                    .build())
        }

        val interceptors = provider.configInterceptors()
        if (!empty(interceptors)) {
            for (interceptor in interceptors!!) {
                builder.addInterceptor(interceptor)
            }
        }

        return builder.build()
    }

    private fun empty(interceptors: Array<Interceptor>?): Boolean = interceptors == null || interceptors.isEmpty()

}