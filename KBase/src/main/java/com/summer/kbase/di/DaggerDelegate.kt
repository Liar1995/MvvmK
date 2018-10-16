package com.summer.kbase.di

import android.app.Application
import com.summer.kbase.di.component.DaggerComponent
import com.summer.kbase.di.component.DaggerDaggerComponent
import com.summer.kbase.di.module.DaggerModule
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:里的 DaggerDelegate 是一个代理类，为了克服 Application 继承的问题，
 * 通过封装一个代理类来对 library 的 Dagger 注入进行管理，然后在需要的 Module 里使用
 */
class DaggerDelegate(private val mApplication: Application) {

    @Inject
    lateinit var mActivityLifecycleCallbacks: DaggerActivityLifecycleCallbacks
    lateinit var mComponent: DaggerComponent

    fun onCreate() {
        //顶级依赖注入
        mComponent = DaggerDaggerComponent.builder().daggerModule(DaggerModule(mApplication)).build()
        mComponent.inject(this)
        //注册 ActivityLifecycleCallbacks 来进行 Activity/Fragment 的依赖注入
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }
}