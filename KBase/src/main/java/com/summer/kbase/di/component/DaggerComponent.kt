package com.summer.kbase.di.component

import android.app.Application
import com.summer.kbase.di.DaggerDelegate
import com.summer.kbase.di.module.DaggerModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:library 的注入器，作用域是 @Singleton
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (DaggerModule::class)])
interface DaggerComponent {

    fun application(): Application
    fun inject(daggerDelegate: DaggerDelegate)

}