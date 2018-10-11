package com.summer.mvvmk.di.module

import com.summer.kbase.di.scope.FragmentScope
import com.summer.mvvmk.entity.Person
import dagger.Module
import dagger.Provides

/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Module
class JordonModule {

    @FragmentScope
    @Provides
    fun provideJordon() = Person("sm", 19)

}