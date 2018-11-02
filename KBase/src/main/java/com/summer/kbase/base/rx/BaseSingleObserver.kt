package com.summer.kbase.base.rx

import com.summer.kbase.R
import com.summer.kbase.app.BaseApplication
import com.summer.kbase.base.IBaseActivity
import com.summer.kbase.common.LoggerUtils
import com.summer.kbase.utils.NetWorkUtils
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.toast
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription


/**
 * Created by sunmeng on 2018/10/25.
 * Email:sunmeng995@gmail.com
 * Description:no Backpressure
 */
open class BaseSingleObserver<T>(private val compositeDisposable: CompositeDisposable) : SingleObserver<T> {

    override fun onSuccess(t: T) {
        LoggerUtils.loggerI("BaseSingleObserver onSuccess")
    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
        LoggerUtils.loggerI("BaseSingleObserver onStart 请求开始之前")
        if (!NetWorkUtils.isNetWorkAvailable(BaseApplication.instance)) {
            BaseApplication.instance.toast(BaseApplication.instance.getString(R.string.network_unavailable))
        }
    }


    override fun onError(e: Throwable) {
        LoggerUtils.loggerE("BaseSingleObserver onError : " + e.printStackTrace())
        //可以在此处处理全局接口异常响应（登录状态已过期）
    }

}