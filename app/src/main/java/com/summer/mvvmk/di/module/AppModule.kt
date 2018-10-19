package com.summer.mvvmk.di.module

import android.app.Application
import com.summer.kbase.di.module.ViewModelFactoryModule
import com.summer.mvvmk.di.builder.ActivityInjectBuilder
import com.summer.mvvmk.di.builder.FragmentInjectBuilder
import dagger.Module

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:提供全局级别的application
 */
@Module(includes = [(ViewModelFactoryModule::class),  (ActivityInjectBuilder::class)])//(FragmentInjectBuilder::class),
class AppModule(private val mApplication: Application)