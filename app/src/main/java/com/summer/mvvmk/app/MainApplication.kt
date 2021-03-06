package com.summer.mvvmk.app

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.summer.kbase.app.BaseApplication
import com.summer.kbase.di.DaggerDelegate
import com.summer.mvvmk.di.component.AppComponent
import com.summer.mvvmk.di.component.DaggerAppComponent
import com.summer.mvvmk.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class MainApplication : BaseApplication(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var mDaggerDelegate: DaggerDelegate

    override fun onCreate() {
        super.onCreate()
        //Library 的依赖注入（顶级）
        mDaggerDelegate = DaggerDelegate(this)
        mDaggerDelegate.onCreate()
        //注入主 Module 中（该 Module 全局）
        DaggerAppComponent.builder().daggerComponent(mDaggerDelegate.mComponent)
                .appModule(AppModule(this)).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = mActivityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentInjector

}