package com.gmail.orlandroyd.jetreddit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.orlandroyd.jetreddit.components.ImagePost
import com.gmail.orlandroyd.jetreddit.components.TextPost
import com.gmail.orlandroyd.jetreddit.domain.model.PostModel
import com.gmail.orlandroyd.jetreddit.domain.model.PostType
import com.gmail.orlandroyd.jetreddit.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val posts: List<PostModel> by viewModel.allPosts.observeAsState(listOf())

    LazyColumn(
        modifier = Modifier.background(color = MaterialTheme.colors.secondary)
    ) {
        items(posts) {
            if (it.type == PostType.TEXT) {
                TextPost(it)
            } else {
                ImagePost(it)
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}