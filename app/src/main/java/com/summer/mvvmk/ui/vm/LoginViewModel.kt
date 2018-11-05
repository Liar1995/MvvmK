package com.summer.mvvmk.ui.vm

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.mpaani.core.networking.Outcome
import com.summer.kbase.base.BaseViewModel
import com.summer.kbase.base.net.livedata.Resource
import com.summer.kbase.binding.command.BindingAction
import com.summer.kbase.binding.command.BindingCommand
import com.summer.kbase.ext.toLiveData
import com.summer.mvvmk.data.protocol.GankResp
import com.summer.mvvmk.repository.api.GankDataContract

/**
 * Created by sunmeng on 2018/10/19.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class LoginViewModel(application: Application, private val repo: GankDataContract.Repository) : BaseViewModel(application) {

    val commentsOutcome: LiveData<Outcome<String>> by lazy {
        repo.commentsFetchOutcome.toLiveData(compositeDisposable)
    }

    var photoListLiveData: LiveData<Resource<GankResp>> = MutableLiveData()

    var getDataByRxOnClickCommand = BindingCommand<Unit>(object : BindingAction {
        override fun call() {
            repo.getGankCategories()
        }
    })


    init {
        photoListLiveData = repo.getGankData()
    }

    var getDataByLiveDataOnClickCommand = BindingCommand<Unit>(object : BindingAction {
        override fun call() {
            //无参数请求
            photoListLiveData = repo.getGankData()
        }
    })

}