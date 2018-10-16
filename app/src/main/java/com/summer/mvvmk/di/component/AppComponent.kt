package com.summer.mvvmk.di.component

import com.summer.kbase.di.component.DaggerComponent
import com.summer.kbase.di.scope.AppScope
import com.summer.mvvmk.app.MainApplication
import com.summer.mvvmk.di.module.AppModule
import com.summer.mvvmk.di.module.MainActivityBuilder
import com.summer.mvvmk.di.module.MainFragmentBuilder
import dagger.Component

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@AppScope
@Component(dependencies = [DaggerComponent::class], modules = [(AppModule::class),
    (MainActivityBuilder::class), (MainFragmentBuilder::class)])
interface AppComponent {
    fun inject(mainApplication: MainApplication)
}