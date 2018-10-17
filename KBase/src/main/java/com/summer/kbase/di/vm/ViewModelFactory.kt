package com.summer.kbase.di.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.summer.kbase.R
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by sunmeng on 2018/10/15.
 * Email:sunmeng995@gmail.com
 * Description:官方的是用反射直接创建，自己实现的是利用dagger的机制试下注入的
 * ViewModel的创建不可直接new，需要使用这个ViewModelProviders才能与Activity或者
 */
class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) throw IllegalArgumentException("unknown model class " + modelClass)
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}