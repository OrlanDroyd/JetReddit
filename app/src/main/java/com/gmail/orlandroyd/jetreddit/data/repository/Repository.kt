package com.gmail.orlandroyd.jetreddit.data.repository

import androidx.lifecycle.LiveData
import com.gmail.orlandroyd.jetreddit.domain.model.PostModel

interface Repository {

    fun getAllPosts(): LiveData<List<PostModel>>

    fun getAllOwnedPosts(): LiveData<List<PostModel>>

    fun getAllSubreddits(searchedText: String): List<String>

    fun insert(post: PostModel)

    fun deleteAll()
}