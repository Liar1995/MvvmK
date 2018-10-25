package com.summer.kbase.base.net

/**
 * Created by sunmeng on 2018/3/14.
 * Email:sunmeng995@gmail.com
 * Description: 异常 基类
 */
data class BaseException(val static:Int,val msg:String):Throwable()