package com.summer.mvvmk.di.module

import android.app.Application
import dagger.Module

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:提供全局级别的application
 */
@Module
class AppModule(private val mApplication: Application)