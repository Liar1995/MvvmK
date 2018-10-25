package com.summer.kbase.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.summer.kbase.BuildConfig

/**
 * Created by sunmeng on 2018/8/1.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@SuppressLint("StaticFieldLeak")
open class BaseApplication : Application() {

    companion object {
        lateinit var instance: Context
    }

    override fun onCreate() {
        super.onCreate()
        initLogger()
        instance = this
    }

    /**
     * 配置Logger
     * */
    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

}