package com.summer.mvvmk

import android.os.Bundle
import android.util.Log
import com.summer.kbase.BR
import com.summer.kbase.app.BaseActivity
import com.summer.mvvmk.databinding.ActivityLoginBinding
import java.util.logging.Logger

/**
 * Created by sunmeng on 2018/8/2.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?) = R.layout.activity_login

    override fun initVariableId() = BR.viewModel

    override fun initViewModel() = LoginViewModel()

    override fun initViewObservable() {
        super.initViewObservable()
        binding.button.setOnClickListener {
            Log.i("summer",viewModel.getStr())
        }
    }

}