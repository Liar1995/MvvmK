package com.summer.kbase.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.Messenger
import android.support.v4.app.FragmentActivity
import com.summer.kbase.common.LoggerUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/17.
 * Email:sunmeng995@gmail.com
 * Description:
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : RxAppCompatActivity(), IBaseActivity {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewModel: VM? = null
    lateinit var binding: V

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
        LoggerUtils.loggerD("viewModelFactory: $viewModelFactory")
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()

    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        viewModel = initViewModel()
        if (viewModel == null) {
            val modelClass: Class<*>
            val type = javaClass.genericSuperclass
            modelClass = if (type is ParameterizedType) {
                type.actualTypeArguments[1] as Class<*>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                BaseViewModel::class.java
            }
            viewModel = createViewModel(this, modelClass as Class<ViewModel>, viewModelFactory) as VM
        }
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        binding.setVariable(initVariableId(), viewModel)
        viewModel?.apply {
            //让ViewModel拥有View的生命周期感应
            lifecycle.addObserver(this)
            //注入RxLifecycle生命周期
            this.injectLifecycleProvider(this@BaseActivity)
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

    fun showDialog(title: String?) {
        //showDialog
    }

    fun dismissDialog() {
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
    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return</T>
     * */
    fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>, factory: ViewModelProvider.Factory): T {
        return ViewModelProviders.of(activity, factory).get(cls)
    }
}