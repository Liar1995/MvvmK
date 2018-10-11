package com.summer.mvvmk.di.module

import com.summer.kbase.di.scope.FragmentScope
import com.summer.mvvmk.ui.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module
abstract class MainFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [JordonModule::class])
    abstract fun contributeMainFragment(): MainFragment
}