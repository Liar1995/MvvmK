package com.summer.kbase.binding.command


interface BindingFunction<T> {
    fun call(): T
}
