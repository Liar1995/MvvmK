package com.summer.mvvmk.repository.api

import com.summer.mvvmk.data.protocol.GankResp
import io.reactivex.Single

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description:Repository定义层
 */
object GankDataContract {

    interface Repository {
        fun getGankData()
    }

    interface Remote {
        fun getGankData(): Single<GankResp>
    }

}