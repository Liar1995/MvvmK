package com.summer.kbase.common

import io.reactivex.Scheduler

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description:模拟不同的线程接口
 */
interface Scheduler {
    fun mainThread(): Scheduler
    fun io(): Scheduler
}