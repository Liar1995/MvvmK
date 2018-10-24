package com.summer.kbase.binding.command

interface BindingConsumer<T> {
    fun call(t: T)
}
