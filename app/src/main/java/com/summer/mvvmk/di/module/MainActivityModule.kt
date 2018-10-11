package com.summer.mvvmk.di.module

import com.summer.kbase.di.scope.ActivityScope
import com.summer.kbase.di.scope.FragmentScope
import com.summer.mvvmk.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module
abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [KobeModule::class])
    abstract fun contributeMainActivity(): MainActivity

}