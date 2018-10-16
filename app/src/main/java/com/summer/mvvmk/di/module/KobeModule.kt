package com.summer.mvvmk.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.summer.kbase.di.scope.ActivityScope
import com.summer.kbase.di.scope.FragmentScope
import com.summer.kbase.di.vm.ViewModelFactory
import com.summer.kbase.di.vm.ViewModelKey
import com.summer.mvvmk.entity.Person
import com.summer.mvvmk.ui.vm.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:注入到mainact的对象
 */
@Module
 class KobeModule {

    @ActivityScope
    @Provides
    fun provideJordon() = Person("lila", 25)

}