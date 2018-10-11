package com.summer.kbase.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module
class DaggerModule(private val mApplication:Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return this.mApplication
    }

}