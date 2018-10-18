package com.summer.mvvmk.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.summer.kbase.di.scope.FragmentScope
import com.summer.kbase.di.vm.ViewModelFactory
import com.summer.kbase.di.vm.ViewModelKey
import com.summer.mvvmk.ui.fragment.MainFragment
import com.summer.mvvmk.ui.vm.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module
abstract class FragmentInjectBuilder {

//    @FragmentScope
//    @ContributesAndroidInjector(modules = [JordonModule::class])
//    abstract fun contributeMainFragment(): MainFragment

}