package com.summer.mvvmk.di.module

import android.app.Application
import android.arch.lifecycle.ViewModel
import com.summer.kbase.di.scope.ActivityScope
import com.summer.kbase.di.scope.AppScope
import com.summer.kbase.di.vm.ViewModelKey
import com.summer.mvvmk.entity.Person
import com.summer.mvvmk.repository.api.GankDataContract
import com.summer.mvvmk.ui.vm.LoginViewModel
import com.summer.mvvmk.ui.vm.MainViewModel
import com.summer.mvvmk.ui.vm.factory.LoginViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sunmeng on 2018/10/19.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module
class LoginVmModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    /*ViewModel*/
    @Provides
    @ActivityScope
    fun loginViewModelFactory(application: Application, repository: GankDataContract.Repository): LoginViewModelFactory = LoginViewModelFactory(application, repository)


}