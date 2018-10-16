package com.summer.mvvmk.ui.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class MainViewModel @Inject constructor() : ViewModel() {

    var userId = MutableLiveData<String>()

    fun changeID(id: String) {
        userId.value = id
    }

}