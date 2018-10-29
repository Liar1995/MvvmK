package com.summer.mvvmk.data.api

import android.arch.lifecycle.LiveData
import com.summer.kbase.base.net.ApiResponse
import com.summer.kbase.base.net.BaseResp
import com.summer.mvvmk.data.protocol.CategoriesResp
import com.summer.mvvmk.data.protocol.GankResp
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
interface GankDataService {

    @GET("/api/data/Android/10/1")
    fun getGankData(): LiveData<ApiResponse<GankResp>>

    @GET("/api/xiandu/categories")
    fun getGankCategories(): Single<CategoriesResp>

}