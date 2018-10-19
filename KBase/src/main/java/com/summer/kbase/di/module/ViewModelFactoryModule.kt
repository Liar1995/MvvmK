package com.summer.kbase.di.module

import android.arch.lifecycle.ViewModelProvider

import com.summer.kbase.di.vm.ViewModelFactory

import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
