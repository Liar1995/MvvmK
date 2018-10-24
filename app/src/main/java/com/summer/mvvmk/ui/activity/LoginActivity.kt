package com.summer.mvvmk.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.summer.kbase.base.BaseActivityByCustomFactory
import com.summer.kbase.base.BaseActivityByDefFactory
import com.summer.mvvmk.BR
import com.summer.mvvmk.R
import com.summer.mvvmk.databinding.ActivityLoginBinding
import com.summer.mvvmk.ui.vm.LoginViewModel
import com.summer.mvvmk.ui.vm.factory.LoginViewModelFactory
import javax.inject.Inject


/**
 * Created by sunmeng on 2018/10/19.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class LoginActivity : BaseActivityByCustomFactory<ActivityLoginBinding, LoginViewModel>() {

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    override fun initContentView(savedInstanceState: Bundle?) = R.layout.activity_login

    override fun initVariableId() = BR.viewModel

    override fun initViewModel(): LoginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

}