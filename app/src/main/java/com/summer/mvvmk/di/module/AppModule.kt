package com.summer.mvvmk.di.module

import android.app.Application
import com.summer.kbase.di.module.ViewModelFactoryModule
import dagger.Module

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:提供全局级别的application
 */
@Module(includes = [(ViewModelFactoryModule::class), (FragmentInjectBuilder::class), (ActivityInjectBuilder::class)])
class AppModule(private val mApplication: Application)