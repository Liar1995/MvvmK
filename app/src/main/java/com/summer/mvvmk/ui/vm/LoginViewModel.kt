package com.summer.mvvmk.ui.vm

import android.app.Application
import android.arch.lifecycle.LiveData
import android.databinding.ObservableInt
import android.view.View
import com.mpaani.core.networking.Outcome
import com.summer.kbase.base.BaseViewModel
import com.summer.kbase.base.net.livedata.FetchLiveData
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

    var progressBarStatus: ObservableInt = ObservableInt(View.GONE)

    val commentsOutcome: LiveData<Outcome<String>> by lazy {
        repo.commentsFetchOutcome.toLiveData(compositeDisposable)
    }

    var gankLiveData: FetchLiveData<Resource<GankResp>> = FetchLiveData<Resource<GankResp>>().createSwitchMap { repo.getGankData() }

    var gankBlend: FetchLiveData<Resource<GankResp>> = FetchLiveData<Resource<GankResp>>().createSwitchMap { repo.getGankDataBlend() }

    var getDataByRxOnClickCommand = BindingCommand<Unit>(object : BindingAction {
        override fun call() {
            repo.getGankCategories()
        }
    })

    var getDataByLiveDataOnClickCommand = BindingCommand<Unit>(object : BindingAction {
        override fun call() {
            //无参数请求
            gankLiveData.trigger()
        }
    })

    var getDataByBlendOnClickCommand = BindingCommand<Unit>(object : BindingAction {
        override fun call() {
            //无参数请求
            gankBlend.trigger()
        }
    })

}