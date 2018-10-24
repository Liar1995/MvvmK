package com.summer.kbase.di.module

import android.app.Application
import com.summer.kbase.common.AppScheduler
import com.summer.kbase.common.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:DaggerModule 主要提供一些全局依赖，okHttpClient，retrofit
 */
@Module
class DaggerModule(private val mApplication: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return this.mApplication
    }

    @Provides
    @Singleton
    fun scheduler(): Scheduler {
        return AppScheduler()
    }

}