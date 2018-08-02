package com.summer.kbase.app

import android.app.Activity
import android.text.TextUtils
import java.util.*

/**
 * Created by sunmeng on 2018/8/1.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class AppManager private constructor() {


    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    //Activity入栈
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    //Activity出栈
    fun popActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    //关闭指定的act
    private fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    //获取当前栈顶
    private fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    //获取当前栈顶
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

    //通过activity名称关闭
    fun closeActivityByName(name: String) {
        if (activityStack == null) {
            return
        }
        var index = activityStack.size - 1

        while (true) {
            val activity = activityStack[index] ?: break

            val activityName = activity.componentName.className
            if (!TextUtils.equals(name, activityName)) {
                index--
                if (index < 0) {//avoid index out of bound.
                    break
                }
                continue
            }
            popActivity(activity)
            finishActivity(activity)
            break
        }
    }

    //检查是否存在某个act
    fun existenceActivity(activity: String): Boolean {
        if (activityStack.isEmpty()) return false
        for (item in activityStack) {
            return item.javaClass.name == activity
        }
        return false
    }

    //获得当前ACTIVITY 名字
    fun getCurrentActivityName(): String {
        val activity = currentActivity()
        var name = ""
        if (activity != null) {
            name = activity.componentName.className
        }
        return name
    }

    //最多打开3个商城页，关闭先入栈的
    fun closeOtherAct() {
        val name = "com.modernsky.goodscenter.ui.activity.GoodsDetailActivity"
        val count = activityStack.count { it.componentName.className == name }
        if (count > 3) {
            activityStack.filter { it.componentName.className == name }
                    .forEach {
                        it.finish()
                        popActivity(it)
                        return@forEach
                    }
        }
    }
}