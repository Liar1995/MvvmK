package com.summer.mvvmk.repository.remote

import android.arch.lifecycle.LiveData
import com.summer.kbase.base.net.BaseResp
import com.summer.kbase.base.net.NextworkLiveDataConverter
import com.summer.kbase.base.net.livedata.ApiResponse
import com.summer.kbase.base.rx.BaseSingleObserver
import com.summer.kbase.ext.execute
import com.summer.mvvmk.data.api.GankDataService
import com.summer.mvvmk.data.protocol.CategoriesResp
import com.summer.mvvmk.data.protocol.GankResp
import com.summer.mvvmk.repository.api.GankDataContract
import io.reactivex.Single

/**
 * Created by sunmeng on 2018/10/24.
 * Email:sunmeng995@gmail.com
 * Description: Repository Contract Remote Impl,provider network data
 */
class GankRemoteData(private val gankService: GankDataService) : GankDataContract.Remote {

    override fun getGankDataBlend(): LiveData<ApiResponse<GankResp>> {
        return NextworkLiveDataConverter.convert(gankService.getGankDataBlend())
    }

    override fun getGankCategories() = gankService.getGankCategories()

    override fun getGankData() = gankService.getGankData()

}