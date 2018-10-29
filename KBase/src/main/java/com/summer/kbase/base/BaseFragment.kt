package com.summer.kbase.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summer.kbase.ext.observe
import java.lang.reflect.ParameterizedType

/**
 * Created by sunmeng on 2018/10/17.
 * Email:sunmeng995@gmail.com
 * Description:
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment(), IBaseActivity {

    var viewModel: VM? = null
    lateinit var binding: V
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false)
        binding.setVariable(initVariableId(), viewModel)
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel!!)
        //注入RxLifecycle生命周期
//        viewModel?.injectLifecycleProvider(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //私有的ViewModel与View的契约事件回调逻辑
        registerUIChangeLiveDataCallBack()
        initData()
        initViewObservable()
    }

    //注册ViewModel与View的契约UI回调事件
    private fun registerUIChangeLiveDataCallBack() {
        viewModel?.let {
            //加载对话框显示
            it.getUC().getShowDialogLiveData().observe(this, Observer<String> { title -> showDialog(title) })
            //加载对话框消失
            it.getUC().getDismissDialogLiveData().observe(this, Observer<Boolean> { dismissDialog() })
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
    abstract fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int

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
    fun initViewModel(): VM? {
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
    fun <T : ViewModel> createViewModel(fragment: Fragment, cls: Class<T>, factory: ViewModelProvider.Factory): T {
        return ViewModelProviders.of(fragment, factory).get(cls)
    }
}