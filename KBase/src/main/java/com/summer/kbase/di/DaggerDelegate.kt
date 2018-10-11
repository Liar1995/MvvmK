package com.summer.kbase.di

import android.app.Application
import com.summer.kbase.di.component.DaggerComponent
import com.summer.kbase.di.component.DaggerDaggerComponent
import com.summer.kbase.di.module.DaggerModule
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
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