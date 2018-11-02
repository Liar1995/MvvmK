package com.summer.kbase.base.net.logging

import okhttp3.internal.platform.Platform

interface Logger {
    fun log(level: Int, tag: String, msg: String)

    companion object {

        val DEFAULT: Logger = object : Logger {
            override fun log(level: Int, tag: String, message: String) {
                Platform.get().log(level, message, null)
            }
        }
    }
}
