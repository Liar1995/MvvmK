package com.summer.kbase.di.module

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.summer.kbase.BuildConfig
import com.summer.kbase.base.BaseContract
import com.summer.kbase.net.NetInterceptor
import com.summer.kbase.net.NetProvider
import com.summer.kbase.net.NetProviderImpl
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
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