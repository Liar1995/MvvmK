package com.summer.mvvmk.di.builder

import com.summer.kbase.di.scope.ActivityScope
import com.summer.mvvmk.di.module.KobeModule
import com.summer.mvvmk.di.module.LoginVmModule
import com.summer.mvvmk.ui.activity.LoginActivity
import com.summer.mvvmk.ui.activity.MainActivity
import com.summer.mvvmk.ui.activity.TwoAct
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module
abstract class ActivityInjectBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [KobeModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginVmModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeTwoActivity(): TwoAct

}