package com.summer.mvvmk.repository.remote

import com.summer.mvvmk.data.api.GankDataService
import com.summer.mvvmk.data.protocol.GankResp
import com.summer.mvvmk.repository.api.GankDataContract
import io.reactivex.Single

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class GankRemoteData(private val gankService: GankDataService) : GankDataContract.Remote {

    override fun getGankData() = gankService.getGankData()

}