package com.summer.mvvmk.repository.api

import android.arch.lifecycle.LiveData
import com.mpaani.core.networking.Outcome
import com.summer.kbase.base.net.livedata.ApiResponse
import com.summer.kbase.base.net.livedata.Resource
import com.summer.mvvmk.data.protocol.CategoriesResp
import com.summer.mvvmk.data.protocol.GankResp
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description:Repository定义层
 */
object GankDataContract {

    //定义该Repository的逻辑操作
    interface Repository {
        fun getGankData(): LiveData<Resource<GankResp>>
        fun getGankCategories()
        val commentsFetchOutcome: PublishSubject<Outcome<String>>
    }

    //network api return type from retrofit return type
    interface Remote {
        fun getGankData(): LiveData<ApiResponse<GankResp>>
        fun getGankCategories(): Single<CategoriesResp>
    }

}