package com.summer.mvvmk.di.component

import com.summer.kbase.di.component.DaggerComponent
import com.summer.kbase.di.scope.AppScope
import com.summer.mvvmk.app.MainApplication
import com.summer.mvvmk.di.module.AppModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 *
 * 不明白为什么这个component需要加上AndroidSupportInjectionModule，明明父DaggerComponent里面关联了这个！！！
 */
@AppScope
@Component(dependencies = [DaggerComponent::class], modules = [(AppModule::class), AndroidSupportInjectionModule::class])
interface AppComponent {

    /**
     * Dagger 注入
     *
     * @param mainApplication MainApp
     */
    fun inject(mainApplication: MainApplication)

}