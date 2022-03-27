package com.gmail.orlandroyd.jetreddit

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gmail.orlandroyd.jetreddit.viewmodel.MainViewModel
import com.gmail.orlandroyd.jetreddit.viewmodel.MainViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(
            this,
            JetRedditApplication(applicationContext).dependencyInjector.repository
        )
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetRedditApp(viewModel)
        }
    }
}