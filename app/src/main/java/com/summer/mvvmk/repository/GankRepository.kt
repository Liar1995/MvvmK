package com.summer.mvvmk.repository

import com.mpaani.core.networking.Outcome
import com.summer.kbase.base.net.BaseResp
import com.summer.kbase.base.rx.BaseSingleObserver
import com.summer.kbase.common.LoggerUtils
import com.summer.kbase.common.Scheduler
import com.summer.kbase.ext.execute
import com.summer.kbase.ext.success
import com.summer.mvvmk.data.protocol.CategoriesResp
import com.summer.mvvmk.repository.api.GankDataContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description: Repository 模块负责处理数据操作，构造中可持有local和remote对象的引用，
 * 根据不同情况，在Repository分别处理local和remote的数据，在统一返回给ViewModel
 * 不同的数据源：数据库数据源，网络数据源，Memory Cache 数据源等
 */
class GankRepository(
        private val remote: GankDataContract.Remote,
        private val compositeDisposable: CompositeDisposable,
        private val scheduler: Scheduler
) : GankDataContract.Repository {

    override val commentsFetchOutcome: PublishSubject<Outcome<String>> = PublishSubject.create<Outcome<String>>()

    override fun getGankCategories() {
        remote.getGankCategories().execute(object : BaseSingleObserver<CategoriesResp>(compositeDisposable) {
            override fun onSuccess(t: CategoriesResp) {
                super.onSuccess(t)
                commentsFetchOutcome.success("xxxx")
            }
        }, scheduler)
    }

    override fun getGankData() = remote.getGankData()

}