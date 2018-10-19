package com.summer.kbase.di.component

import android.app.Application
import com.summer.kbase.di.DaggerDelegate
import com.summer.kbase.di.module.DaggerModule
import com.summer.kbase.di.module.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:DaggerComponent 顶级注入器，作用域是 @Singleton
 */
@Singleton
@Component(modules = [(ViewModelFactoryModule::class), (AndroidSupportInjectionModule::class), (DaggerModule::class)])
interface DaggerComponent {

    /**
     * 获取 Application
     *
     * @return Application
     */
    fun application(): Application

    /**
     * Dagger 注入
     *
     * @param daggerDelegate ArmsInjector
     */
    fun inject(daggerDelegate: DaggerDelegate)

}