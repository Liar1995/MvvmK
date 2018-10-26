package com.summer.kbase.di.module

import android.app.Application
import com.google.gson.Gson
import com.summer.kbase.base.BaseContract
import dagger.Module
import dagger.Provides
import ja.burhanrashid52.base.liveUtils.LiveDataCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by sunmeng on 2018/10/23.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module(includes = [OkHttpClientModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
            liveDataCallAdapterFactory: LiveDataCallAdapterFactory,
            okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BaseContract.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addCallAdapterFactory(liveDataCallAdapterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun providesLiveDataCallAdapterFactory(): LiveDataCallAdapterFactory {
        return LiveDataCallAdapterFactory()
    }

    @Provides
    @Singleton
    fun providesOkhttpCache(context: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

}