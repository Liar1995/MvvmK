package com.summer.mvvmk.di.component

import com.summer.kbase.di.component.DaggerComponent
import com.summer.kbase.di.scope.AppScope
import com.summer.mvvmk.app.MainApplication
import com.summer.mvvmk.data.api.GankDataService
import com.summer.mvvmk.di.module.AppModule
import dagger.Component
import io.reactivex.Scheduler

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@AppScope
@Component(dependencies = [DaggerComponent::class], modules = [(AppModule::class)])
interface AppComponent {

    /**
     * Dagger 注入
     *
     * @param mainApplication MainApp
     */
    fun inject(mainApplication: MainApplication)

    /**
     * 注入该model的api
     *
     */
    fun postService(): GankDataService

}