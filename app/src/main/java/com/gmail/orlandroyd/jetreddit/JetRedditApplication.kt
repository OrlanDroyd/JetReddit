package com.gmail.orlandroyd.jetreddit

import android.app.Application
import android.content.Context
import com.gmail.orlandroyd.jetreddit.dependencyinjection.DependencyInjector
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class JetRedditApplication(context : Context) : Application() {

    val dependencyInjector: DependencyInjector = DependencyInjector(context)

}