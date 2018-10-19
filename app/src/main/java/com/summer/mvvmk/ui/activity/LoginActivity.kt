package com.summer.mvvmk.ui.activity

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.summer.kbase.base.BaseActivity
import com.summer.mvvmk.BR
import com.summer.mvvmk.R
import com.summer.mvvmk.databinding.ActivityLoginBinding
import com.summer.mvvmk.ui.vm.LoginViewModel
import javax.inject.Inject
import android.databinding.adapters.TextViewBindingAdapter.setText
import com.summer.kbase.common.LoggerUtils
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode



/**
 * Created by sunmeng on 2018/10/19.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?) = R.layout.activity_login

    override fun initVariableId() = BR.viewModel

}