package com.gmail.orlandroyd.jetreddit

import android.app.Application
import com.gmail.orlandroyd.jetreddit.dependencyinjection.DependencyInjector
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(DelicateCoroutinesApi::class)
class JetRedditApplication : Application() {

    lateinit var dependencyInjector: DependencyInjector

    override fun onCreate() {
        super.onCreate()
        initDependencyInjector()
    }

    private fun initDependencyInjector() {
        dependencyInjector = DependencyInjector(this)
    }
}