package com.summer.kbase.di

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.summer.kbase.common.LoggerUtils
import dagger.android.support.AndroidSupportInjection
import java.util.logging.Logger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:FragmentManager.FragmentLifecycleCallbacks代理，在ActivityLifecycleCallbacks统一管理
 */
@Singleton
class DaggerFragmentLifecycleCallbacks @Inject constructor() : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentAttached(fm: FragmentManager?, f: Fragment?, context: Context?) {
        super.onFragmentAttached(fm, f, context)
        AndroidSupportInjection.inject(f)
        LoggerUtils.loggerI(f.toString() + " ---> onFragmentAttached")
    }

    override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        LoggerUtils.loggerI(f.toString() + " ---> onFragmentCreated")
    }

    override fun onFragmentDestroyed(fm: FragmentManager?, f: Fragment?) {
        super.onFragmentDestroyed(fm, f)
        LoggerUtils.loggerI(f.toString() + " ---> onFragmentDestroyed")
    }
}