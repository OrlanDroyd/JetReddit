package com.gmail.orlandroyd.jetreddit.screens

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.orlandroyd.jetreddit.R
import com.gmail.orlandroyd.jetreddit.domain.model.PostModel
import com.gmail.orlandroyd.jetreddit.routing.JetRedditRouter
import com.gmail.orlandroyd.jetreddit.routing.Screen
import com.gmail.orlandroyd.jetreddit.viewmodel.MainViewModel

@Composable
fun AddScreen(viewModel: MainViewModel) {

    val selectedCommunity: String by viewModel.selectedCommunity.observeAsState("")

    var post by remember { mutableStateOf(PostModel.EMPTY) }

    Column(modifier = Modifier.fillMaxSize()) {

        CommunityPicker(selectedCommunity)

        TitleTextField(post.title) { newTitle -> post = post.copy(title = newTitle) }

        BodyTextField(post.text) { newContent -> post = post.copy(text = newContent) }

        AddPostButton(selectedCommunity.isNotEmpty() && post.title.isNotEmpty()) {
            viewModel.savePost(post)
            JetRedditRouter.navigateTo(Screen.Home)
        }
    }
}

/**
 * Input view for the post title
 */
@Composable
private fun TitleTextField(text: String, onTextChange: (String) -> Unit) {
    val activeColor = MaterialTheme.colors.onSurface

    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(stringResource(R.string.title)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = activeColor,
            focusedLabelColor = activeColor,
            cursorColor = activeColor,
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}

/**
 * Input view for the post body
 */
@Composable
private fun BodyTextField(text: String, onTextChange: (String) -> Unit) {
    val activeColor = MaterialTheme.colors.onSurface

    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(stringResource(R.string.body_text)) },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 240.dp)
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = activeColor,
            focusedLabelColor = activeColor,
            cursorColor = activeColor,
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}

/**
 * Input view for the post body
 */
@Composable
private fun AddPostButton(isEnabled: Boolean, onSaveClicked: () -> Unit) {
    Button(
        onClick = onSaveClicked,
        enabled = isEnabled,
        content = {
            Text(
                text = stringResource(R.string.save_post),
                color = MaterialTheme.colors.onSurface
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 240.dp)
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp),
    )
}

@Composable
private fun CommunityPicker(selectedCommunity: String) {

    val selectedText =
        if (selectedCommunity.isEmpty()) stringResource(R.string.choose_community) else selectedCommunity

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 240.dp)
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp)
            .clickable {
                JetRedditRouter.navigateTo(Screen.ChooseCommunity)
            },
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.subreddit_placeholder),
            contentDescription = stringResource(id = R.string.subreddits),
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
        )

        Text(
            text = selectedText,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}