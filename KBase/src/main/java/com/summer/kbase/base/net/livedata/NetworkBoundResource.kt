package com.summer.kbase.base.net.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 *
 * @param <ResultType>
 * @param <RequestType>
 * </RequestType></ResultType>
 */
abstract class NetworkBoundResource<ResultType, RequestType> @MainThread
constructor(private val appExecutors: AppExecutors) {

    //MediatorLiveData类是自定义LiveData，可以观察其他LiveData对象并且在MediatorLiveData处于active时执行Observer的onChange()回调。
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        //首先通知LiveData为加载中状态
        result.value = Resource.loading(null)
        val dbSource by lazy { loadFromDb() }
        fetchFromNetwork(dbSource)
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            response.let {
                if (it?.isSuccessful!!) {
                    appExecutors.diskIO.execute {
                        appExecutors.mainThread.execute {
                            result.addSource(loadFromDb()) {
                                newData -> result.setValue(Resource.success(newData)) }
                        }
                    }
                } else {
                    onFetchFailed()
                    result.addSource(dbSource) { newData -> result.setValue(Resource.error(it.errorMessage, newData)) }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    /**
     * Called when the fetch to the network has failed
     */
    protected fun onFetchFailed() {}

    //@WorkerThread
    //private fun processResponse(response: ApiResponse<RequestType>): RequestType? {
    //    return response.body
    //}

    /**
     * Called when the data need to be saved to the DB
     *
     * The data has been fetch from the network and can, now, be saved to the disk.
     *
     * @param item The data to save
     */
    //@WorkerThread
    //protected abstract fun saveCallResult(item: RequestType)

    /**
     * Called to determine whether the data should be fetched from the network or only from the DB
     *
     * @param data The data currently saved in the disk
     * @return Whether the data should be fetched from the network or only from the DB. If it's
     * false, the data cached in the DB will be returned. If it's true, the cached data will be
     * fetched from the DB and returned while the new data is being fetched from the network.
     */
    //@MainThread
    //protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    /**
     * Called in order to get a [LiveData] that will be observed in order to get the data from the
     * webservice
     *
     * @return The LiveData to observe in order to get the data from the webservice
     */
    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}