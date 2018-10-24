package com.summer.mvvmk.data.api

import com.summer.mvvmk.data.protocol.GankResp
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
interface GankDataService {

    @GET("/10/1")
    fun getGankData(): Single<GankResp>

}