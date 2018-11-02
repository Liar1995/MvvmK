package com.summer.kbase.ext

import com.summer.kbase.base.net.BaseFunc
import com.summer.kbase.base.net.BaseFuncResponse
import com.summer.kbase.base.net.BaseResp
import com.summer.kbase.base.rx.BaseObserver
import com.summer.kbase.base.rx.BaseSingleObserver
import com.summer.kbase.common.AppScheduler
import com.summer.kbase.common.Scheduler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Completable]
 * */
fun Completable.performOnBackOutOnMain(scheduler: Scheduler): Completable {
    return this.subscribeOn(scheduler.io())
            .observeOn(scheduler.mainThread())
}

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Flowable]
 * */
fun <T> Flowable<T>.performOnBackOutOnMain(scheduler: Scheduler): Flowable<T> {
    return this.subscribeOn(scheduler.io())
            .observeOn(scheduler.mainThread())
}

/**
 * Extension function to subscribe on the background thread and observe on the main thread  for a [Single]
 * */
fun <T> Single<T>.performOnBackOutOnMain(scheduler: Scheduler): Single<T> {
    return this.subscribeOn(scheduler.io())
            .observeOn(scheduler.mainThread())
}

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Observable]
 * */
fun <T> Observable<T>.performOnBackOutOnMain(scheduler: Scheduler): Observable<T> {
    return this.subscribeOn(scheduler.io())
            .observeOn(scheduler.mainThread())
}

/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}


/**
 * Extension function to subscribe on the background thread for a Flowable
 * */
fun <T> Flowable<T>.performOnBack(scheduler: Scheduler): Flowable<T> {
    return this.subscribeOn(scheduler.io())
}

/**
 * Extension function to subscribe on the background thread for a Completable
 * */
fun Completable.performOnBack(scheduler: Scheduler): Completable {
    return this.subscribeOn(scheduler.io())
}

/**
 * Extension function to subscribe on the background thread for a Observable
 * */
fun <T> Observable<T>.performOnBack(scheduler: Scheduler): Observable<T> {
    return this.subscribeOn(scheduler.io())
}

fun <T> Observable<T>.execute(subscriber: BaseObserver<T>, scheduler: Scheduler) {
    this.subscribeOn(scheduler.io())
            .observeOn(scheduler.mainThread())
            .subscribe(subscriber)
}

fun <T> Single<T>.execute(subscriber: BaseSingleObserver<T>, scheduler: Scheduler) {
    this.subscribeOn(scheduler.io())
            .observeOn(scheduler.mainThread())
            .subscribe(subscriber)
}

//扩展数据转换restful api
fun <T> Observable<Response<T>>.convertResponse(): Observable<T> {
    return this.flatMap(BaseFuncResponse())
}

//扩展数据转换common
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}