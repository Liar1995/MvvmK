package com.summer.kbase.common

import com.orhanobut.logger.Logger

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
object LoggerUtils {
    fun <T> loggerE(msg: T) {
        Logger.e(msg.toString())
    }

    fun <T> loggerD(msg: T) {
        Logger.d(msg.toString())
    }

    fun <T> loggerW(msg: T) {
        Logger.w(msg.toString())
    }

    fun <T> loggerV(msg: T) {
        Logger.v(msg.toString())
    }

    fun <T> loggerI(msg: T) {
        Logger.i(msg.toString())
    }

    fun <T> loggerWtf(msg: T) {
        Logger.wtf(msg.toString())
    }

    fun <T> loggerJson(msg: T) {
        Logger.json(msg.toString())
    }

    fun <T> loggerXml(msg: T) {
        Logger.xml(msg.toString())
    }
}