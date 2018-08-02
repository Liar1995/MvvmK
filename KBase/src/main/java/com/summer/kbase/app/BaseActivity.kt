package com.summer.kbase.app

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by sunmeng on 2018/8/2.
 * Email:sunmeng995@gmail.com
 * Description:
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : RxAppCompatActivity(), IBaseActivity {

    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()

        initViewDataBinding(savedInstanceState)

        initData()

        initViewObservable()
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        viewModel = initViewModel()
        binding.setVariable(initVariableId(), viewModel)
        lifecycle.addObserver(viewModel)
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(savedInstanceState: Bundle?): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    abstract fun initViewModel(): VM

    /**
     * 初始化
     * */
    override fun initParam() {

    }

    override fun initData() {

    }

    override fun initViewObservable() {

    }
}