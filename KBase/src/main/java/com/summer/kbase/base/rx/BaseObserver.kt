package com.summer.kbase.base.rx

import com.summer.kbase.R
import com.summer.kbase.app.BaseApplication
import com.summer.kbase.base.IBaseActivity
import com.summer.kbase.common.LoggerUtils
import com.summer.kbase.utils.NetWorkUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription


/**
 * Created by sunmeng on 2018/10/25.
 * Email:sunmeng995@gmail.com
 * Description:no Backpressure
 */
open class BaseObserver<T>(private val baseView: IBaseActivity) : Observer<T> {

    private lateinit var mDisposable: Disposable

    override fun onSubscribe(d: Disposable) {
        mDisposable = d
        LoggerUtils.loggerI("BaseObserver onStart 请求开始之前")
        if (!NetWorkUtils.isNetWorkAvailable(BaseApplication.instance)) {
            baseView.hideLoading()
            baseView.onError(BaseApplication.instance.getString(R.string.network_unavailable))
//            if (mRefresh != null) {
//                mRefresh.finishLoadmore()
//                mRefresh.finishRefresh()
//            }
//            unsubscribe()
        }
    }

    override fun onNext(t: T) {
        LoggerUtils.loggerI("BaseObserver onNext")
    }

    override fun onError(e: Throwable) {
        LoggerUtils.loggerE("BaseObserver onError : " + e.printStackTrace())
        baseView.hideLoading()
//        if (mRefresh != null) {
//            mRefresh.finishLoadmore()
//            mRefresh.finishRefresh()
//        }
//        if (e is BaseException) {
//            if (e.static == 401) {
//                SampleApplicationLike.instance.toast("你的登录状态已过期")
//                BaseSignUtils.logout("/userCenter/login")
//            } else {
//                baseView.onError(e.msg)
//            }
//        }
    }

    override fun onComplete() {
        LoggerUtils.loggerI("BaseObserver onCompleted")
    }

}