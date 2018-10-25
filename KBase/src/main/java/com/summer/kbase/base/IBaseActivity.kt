package com.summer.kbase.base

/**
 * Created by sunmeng on 2018/10/18.
 * Email:sunmeng995@gmail.com
 * Description:
 */
interface IBaseActivity {

    /**
     * 展示界面加载状态
     */
    fun showLoading()

    /**
     * 隐藏界面加载状态
     */
    fun hideLoading()

    /**
     * 展示错误信息（toast）
     */
    fun onError(message: String)

    /**
     * 初始化界面传递参数
     */
    fun initParam()

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 初始化界面观察者的监听
     */
    fun initViewObservable()


}