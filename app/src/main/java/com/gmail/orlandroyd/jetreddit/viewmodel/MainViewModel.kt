package com.gmail.orlandroyd.jetreddit.viewmodel

import androidx.lifecycle.ViewModel
import com.gmail.orlandroyd.jetreddit.data.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

  val allPosts by lazy { repository.getAllPosts() }

  val myPosts by lazy { repository.getAllOwnedPosts() }
}