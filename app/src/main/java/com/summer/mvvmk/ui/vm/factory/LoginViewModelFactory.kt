package com.summer.mvvmk.ui.vm.factory

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.summer.mvvmk.repository.api.GankDataContract
import com.summer.mvvmk.ui.vm.LoginViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val application: Application, private val repository: GankDataContract.Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(application, repository) as T
    }

}