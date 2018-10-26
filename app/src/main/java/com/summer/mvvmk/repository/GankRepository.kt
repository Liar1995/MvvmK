package com.summer.mvvmk.repository

import com.summer.kbase.base.rx.BaseSingleObserver
import com.summer.kbase.common.LoggerUtils
import com.summer.kbase.common.Scheduler
import com.summer.kbase.ext.execute
import com.summer.mvvmk.data.protocol.GankResp
import com.summer.mvvmk.repository.api.GankDataContract
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class GankRepository(
        private val remote: GankDataContract.Remote,
        private val scheduler: Scheduler
) : GankDataContract.Repository {

    override fun getGankData() = remote.getGankData()

}