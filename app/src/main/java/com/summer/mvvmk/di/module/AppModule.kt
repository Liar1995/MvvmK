package com.summer.mvvmk.di.module

import android.app.Application
import com.summer.kbase.common.Scheduler
import com.summer.kbase.di.module.ViewModelFactoryModule
import com.summer.kbase.di.scope.AppScope
import com.summer.mvvmk.data.api.GankDataService
import com.summer.mvvmk.di.builder.ActivityInjectBuilder
import com.summer.mvvmk.di.builder.FragmentInjectBuilder
import com.summer.mvvmk.repository.GankRepository
import com.summer.mvvmk.repository.api.GankDataContract
import com.summer.mvvmk.repository.remote.GankRemoteData
import com.summer.mvvmk.ui.vm.factory.LoginViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:提供全局级别的application
 */
@Module(includes = [(ViewModelFactoryModule::class), (ActivityInjectBuilder::class), (FragmentInjectBuilder::class)])
class AppModule(val mApplication: Application) {

    /**
     * Parent providers to dependents
     * 按照google aac模式 后期可以再此处注入DB
     * */
    @Provides
    @AppScope
    fun postService(retrofit: Retrofit): GankDataService = retrofit.create(GankDataService::class.java)

    /*Repository*/
    @Provides
    @AppScope
    fun listRepo(remote: GankDataContract.Remote, scheduler: Scheduler): GankDataContract.Repository = GankRepository(remote, scheduler)

    @Provides
    @AppScope
    fun remoteData(postService: GankDataService): GankDataContract.Remote = GankRemoteData(postService)

    @Provides
    @AppScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
}