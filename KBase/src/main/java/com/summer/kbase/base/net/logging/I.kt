package com.summer.kbase.base.net.logging


import java.util.logging.Level

import okhttp3.internal.platform.Platform


internal open class I protected constructor() {

    init {
        throw UnsupportedOperationException()
    }

    companion object {

        fun log(type: Int, tag: String, msg: String) {
            val logger = java.util.logging.Logger.getLogger(tag)
            when (type) {
                Platform.INFO -> logger.log(Level.INFO, msg)
                else -> logger.log(Level.WARNING, msg)
            }
        }
    }
}
