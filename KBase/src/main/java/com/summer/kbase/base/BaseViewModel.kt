package com.summer.kbase.base

import android.app.Application
import android.arch.lifecycle.*
import android.content.Context
import android.util.Log
import com.summer.kbase.common.LoggerUtils
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/17.
 * Email:sunmeng995@gmail.com
 * Description:
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), IBaseViewModel {

    private var uc: UIChangeLiveData? = null

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    /**
     * 注入CompositeDisposable管理对象
     *
     * @param compositeDisposable
     * */
    fun injectCompositeDisposableProvider(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }

    fun getUC(): UIChangeLiveData {
        if (uc == null) {
            uc = UIChangeLiveData()
        }
        return uc as UIChangeLiveData
    }

    inner class UIChangeLiveData : LiveData<Any>() {
        private var showDialogLiveData: MutableLiveData<Any>? = null//String
        private var dismissDialogLiveData: MutableLiveData<Any>? = null//Boolean
        private var finishLiveData: MutableLiveData<Any>? = null//Boolean

        fun getShowDialogLiveData(): MutableLiveData<String> {
            showDialogLiveData = createLiveData(showDialogLiveData)
            return showDialogLiveData as MutableLiveData<String>
        }

        fun getDismissDialogLiveData(): MutableLiveData<Boolean> {
            dismissDialogLiveData = createLiveData(dismissDialogLiveData)
            return dismissDialogLiveData as MutableLiveData<Boolean>
        }

        fun getFinishLiveData(): MutableLiveData<Boolean> {
            finishLiveData = createLiveData(finishLiveData)
            return finishLiveData as MutableLiveData<Boolean>
        }

        private fun createLiveData(liveData: MutableLiveData<Any>?): MutableLiveData<Any> {
            var data = liveData
            if (data == null) {
                data = MutableLiveData()
            }
            return data
        }
    }

    /**
     * An {@link Event Event} constant that can be used to match all events.
     * */
    override fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {
//        LoggerUtils.loggerD("BaseViewModel onAny")
    }

    override fun onCreate() {
        LoggerUtils.loggerD("BaseViewModel onCreate")
    }

    override fun onDestroy() {
        LoggerUtils.loggerD("BaseViewModel onDestroy")
    }

    override fun onStop() {
        LoggerUtils.loggerD("BaseViewModel onStop")
    }

    override fun onResume() {
        LoggerUtils.loggerD("BaseViewModel onResume")
    }

    override fun onPause() {
        LoggerUtils.loggerD("BaseViewModel onPause")
    }

    override fun onStart() {
        LoggerUtils.loggerD("BaseViewModel onStart")
    }

    override fun onCleared() {
        super.onCleared()
        LoggerUtils.loggerD("BaseViewModel onCleared")
        compositeDisposable.let {
            if (it.size() > 0) {
                LoggerUtils.loggerD("compositeDisposable clear size : ${it.size()}")
                it.clear()
                LoggerUtils.loggerD("compositeDisposable clear size : ${it.size()}")
            }
        }
    }

}