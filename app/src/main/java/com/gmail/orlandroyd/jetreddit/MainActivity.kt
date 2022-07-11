package com.gmail.orlandroyd.jetreddit

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import com.gmail.orlandroyd.jetreddit.viewmodel.MainViewModel
import com.gmail.orlandroyd.jetreddit.viewmodel.MainViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(
            this,
            (application as JetRedditApplication).dependencyInjector.repository
        )
    })

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetRedditApp(viewModel)
        }
    }
}