package com.summer.kbase.base.net

import android.arch.lifecycle.LiveData

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


object NextworkLiveDataConverter {

    fun <T> convert(single: Single<T>): LiveData<T> {
        return object : LiveData<T>() {
            var disposable: Disposable? = null

            override fun onActive() {
                super.onActive()
                disposable = single
                        .subscribeOn(Schedulers.io())
                        .subscribe(Consumer<T> { this.postValue(it) })
            }

            override fun onInactive() {
                super.onInactive()
                if (disposable != null) disposable!!.dispose()
            }
        }
    }
}
