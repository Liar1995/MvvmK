package com.summer.kbase.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.greenrobot.eventbus.EventBus
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/17.
 * Email:sunmeng995@gmail.com
 * Description:Activity基类，使用自定义ViewModelFactory对象构建ViewModel，自定义对象可使用后续Repository逻辑
 */
abstract class BaseActivityByCustomFactory<V : ViewDataBinding, VM : BaseViewModel> : RxAppCompatActivity(), IBaseActivity {

    private lateinit var binding: V
    var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //页面接受的参数方法
        initParam()
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState)
        //私有的ViewModel与View的契约事件回调逻辑
        registerUIChangeLiveDataCallBack()
        //页面数据初始化方法
        initData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        viewModel = initViewModel()
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        binding.setVariable(initVariableId(), viewModel)
        viewModel?.apply {
            //让ViewModel拥有View的生命周期感应
            lifecycle.addObserver(this)
            //注入RxLifecycle生命周期
            this.injectLifecycleProvider(this@BaseActivityByCustomFactory)
        }
    }

    //注册ViewModel与View的契约UI回调事件
    private fun registerUIChangeLiveDataCallBack() {
        viewModel?.let {
            //加载对话框显示
            it.getUC().getShowDialogLiveData().observe(this, Observer<String> { title -> showDialog(title) })
            //加载对话框消失
            it.getUC().getDismissDialogLiveData().observe(this, Observer<Boolean> { dismissDialog() })
            //关闭界面
            it.getUC().getFinishLiveData().observe(this, Observer<Boolean> { finish() })
        }

    }

    open fun showDialog(title: String?) {
        //showDialog
    }

    open fun dismissDialog() {
        //dismissDialog
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
    open fun initViewModel(): VM? {
        return null
    }

    override fun initParam() {

    }

    override fun initData() {

    }

    override fun initViewObservable() {

    }

    override fun onDestroy() {
        super.onDestroy()
        //解除ViewModel生命周期感应
        lifecycle.removeObserver(viewModel!!)
        viewModel = null
        binding.unbind()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    /**
     * 是否使用 EventBus
     *
     * @return True if use
     */
    protected open fun useEventBus(): Boolean {
        return false
    }

}