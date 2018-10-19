package com.summer.kbase.base

import android.app.Application
import android.arch.lifecycle.*
import com.trello.rxlifecycle2.LifecycleProvider
import org.greenrobot.eventbus.EventBus

/**
 * Created by sunmeng on 2018/10/17.
 * Email:sunmeng995@gmail.com
 * Description:
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), IBaseViewModel {
    private var uc: UIChangeLiveData? = null
    private lateinit var lifecycle: LifecycleProvider<*>

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    fun injectLifecycleProvider(lifecycle: LifecycleProvider<*>) {
        this.lifecycle = lifecycle
    }

    fun getLifecycleProvider(): LifecycleProvider<*> {
        return lifecycle
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

    override fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {

    }

    override fun onCreate() {

    }

    override fun onDestroy() {

    }

    override fun onStart() {
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onStop() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    /**
     * 是否使用 EventBus
     *
     * @return True if use
     */
    protected open fun useEventBus(): Boolean {
        return true
    }

}