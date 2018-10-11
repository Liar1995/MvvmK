package com.summer.kbase.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class DaggerActivityLifecycleCallbacks @Inject constructor() : Application.ActivityLifecycleCallbacks {

    @Inject
    lateinit var mFragmentLifecycleCallbacks: DaggerFragmentLifecycleCallbacks

    override fun onActivityPaused(p0: Activity?) {
    }

    override fun onActivityResumed(p0: Activity?) {
    }

    override fun onActivityStarted(p0: Activity?) {
    }

    override fun onActivityDestroyed(p0: Activity?) {
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
    }

    override fun onActivityStopped(p0: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, p1: Bundle?) {
        AndroidInjection.inject(activity)
        //如果该 Activity 的 Fragment 需要 Dagger 注入，即实现了 HasSupportFragmentInjector，
        //就会注册上一步的 DaggerFragmentLifecycleCallbacks 来实现 Dagger 注入。
        activity?.let {
            if ((activity is HasSupportFragmentInjector || activity.application is HasSupportFragmentInjector) && activity is FragmentActivity) {
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(mFragmentLifecycleCallbacks, true)
            }
        }
    }
}