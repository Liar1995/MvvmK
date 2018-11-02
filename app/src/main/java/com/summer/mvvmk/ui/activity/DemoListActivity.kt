package com.summer.mvvmk.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.mpaani.core.networking.Outcome
import com.summer.kbase.base.BaseActivityByCustomFactory
import com.summer.kbase.base.net.livedata.Status
import com.summer.kbase.ext.observe
import com.summer.mvvmk.BR
import com.summer.mvvmk.R
import com.summer.mvvmk.databinding.ActivityDemoListBinding
import com.summer.mvvmk.ui.vm.LoginViewModel
import com.summer.mvvmk.ui.vm.factory.LoginViewModelFactory
import org.jetbrains.anko.toast
import javax.inject.Inject


/**
 * Created by sunmeng on 2018/10/19.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class DemoListActivity : BaseActivityByCustomFactory<ActivityDemoListBinding, LoginViewModel>() {

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    override fun initContentView(savedInstanceState: Bundle?) = R.layout.activity_demo_list

    override fun initVariableId() = BR.viewModel

    override fun initViewModel(): LoginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

    override fun initViewObservable() {
        super.initViewObservable()

        with(viewModel) {
            this?.let { _ ->
                //rx事件流
                commentsOutcome.observe(this@DemoListActivity, Observer { it ->
                    when (it) {
                        is Outcome.Progress -> isShowLoading(it.loading)
                        is Outcome.Success -> toast(it.data)
                        is Outcome.Failure -> toast(it.e.message.toString())
                    }
                })

                //livedata 事件流
                photoListLiveData.observe(this@DemoListActivity, Observer {
                    when (it?.status) {
                        Status.SUCCESS -> toast("livedata 事件流 SUCCESS")
                        Status.ERROR -> toast("livedata 事件流 ERROR")
                        Status.LOADING -> toast("livedata 事件流 LOADING")
                    }
                })
            }
        }
    }

}