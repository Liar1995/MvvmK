package com.summer.mvvmk.ui.vm

import android.app.Application
import android.arch.lifecycle.LiveData
import com.mpaani.core.networking.Outcome
import com.summer.kbase.app.AppManager
import com.summer.kbase.base.BaseViewModel
import com.summer.kbase.binding.command.BindingAction
import com.summer.kbase.binding.command.BindingCommand
import com.summer.kbase.common.LoggerUtils
import com.summer.kbase.ext.toLiveData
import com.summer.mvvmk.repository.api.GankDataContract
import io.reactivex.disposables.CompositeDisposable
import org.w3c.dom.Comment
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/19.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class LoginViewModel(application: Application, val repo: GankDataContract.Repository) : BaseViewModel(application) {

    val commentsOutcome: LiveData<Outcome<String>> by lazy {
        repo.commentsFetchOutcome.toLiveData(compositeDisposable)
    }

    var getDataOnClickCommand = BindingCommand<Unit>(object : BindingAction {
        override fun call() {
            getData()
        }
    })

    fun getData() = repo.getGankCategories()

}