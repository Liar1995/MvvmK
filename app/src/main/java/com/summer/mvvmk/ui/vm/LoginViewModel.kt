package com.summer.mvvmk.ui.vm

import android.app.Application
import com.summer.kbase.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/19.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class LoginViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    /**
     * 是否使用 EventBus
     *
     * @return True if use
     */
    override fun useEventBus(): Boolean {
        return false
    }

}