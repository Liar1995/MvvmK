package com.summer.kbase.base.net

data class BaseResp<out T>(val status: Int, val message: String, val data: T)