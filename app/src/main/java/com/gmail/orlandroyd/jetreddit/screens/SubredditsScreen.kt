package com.gmail.orlandroyd.jetreddit.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.gmail.orlandroyd.jetreddit.R
import com.gmail.orlandroyd.jetreddit.components.BackgroundText
import com.gmail.orlandroyd.jetreddit.models.SubredditModel

val subreddits = listOf(
    SubredditModel(
        R.string.raywenderlich,
        R.string.members_120k,
        R.string.welcome_to_raywenderlich
    ),
    SubredditModel(
        R.string.programming,
        R.string.members_600k,
        R.string.hello_programmers
    ),
    SubredditModel(
        R.string.android,
        R.string.members_400k,
        R.string.welcome_to_android
    ),
    SubredditModel(
        R.string.androiddev,
        R.string.members_500k,
        R.string.hello_android_devs
    )
)

val mainCommunities = listOf(R.string.all, R.string.public_network)

val communities = listOf(
    R.string.digitalnomad,
    R.string.covid19,
    R.string.memes,
    R.string.humor,
    R.string.worldnews,
    R.string.dogs,
    R.string.cats
)

@Composable
fun SubredditsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = modifier.padding(16.dp),
            text = stringResource(R.string.recently_visited_subreddits),
            fontSize = 12.sp,
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow(
            modifier = modifier.padding(end = 16.dp)
        ) {
            items(subreddits) { Subreddit(it) }
        }
        Communities(modifier)
    }
}

@Composable
fun Subreddit(subredditModel: SubredditModel, modifier: Modifier = Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .size(120.dp)
            .padding(
                start = 2.dp,
                end = 2.dp,
                top = 4.dp,
                bottom = 4.dp
            )
    ) {
        SubredditBody(subredditModel)
    }
}

@Composable
fun SubredditBody(subredditModel: SubredditModel, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        val (backgroundImage, icon, name, members, description) = createRefs() // 1

        SubredditImage( // 2
            modifier = modifier.constrainAs(backgroundImage) {
                centerHorizontallyTo(parent)
                top.linkTo(parent.top)
            }
        )

        SubredditIcon( // 3
            modifier = modifier.constrainAs(icon) {
                top.linkTo(backgroundImage.bottom)
                bottom.linkTo(backgroundImage.bottom)
                centerHorizontallyTo(parent)
            }
                .zIndex(1f)
        )

        SubredditName( // 4
            nameStringRes = subredditModel.nameStringRes,
            modifier = modifier.constrainAs(name) {
                top.linkTo(icon.bottom)
                centerHorizontallyTo(parent)
            }
        )

        SubredditMembers( // 5
            membersStringRes = subredditModel.membersStringRes,
            modifier = modifier.constrainAs(members) {
                top.linkTo(name.bottom)
                centerHorizontallyTo(parent)
            }
        )

        SubredditDescription( // 6
            descriptionStringRes = subredditModel.descriptionStringRes,
            modifier = modifier.constrainAs(description) {
                top.linkTo(members.bottom)
                centerHorizontallyTo(parent)
            }
        )
    }
}

@Composable
fun SubredditImage(modifier: Modifier) {
    Image(
        painter = ColorPainter(Color.Blue),
        contentDescription = stringResource(id = R.string.subreddit_image),
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)
    )
}

@Composable
fun SubredditIcon(modifier: Modifier) {
    Icon(
        modifier = modifier,
        tint = Color.LightGray,
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_planet),
        contentDescription = stringResource(id = R.string.subreddit_icon),
    )
}

@Composable
fun SubredditName(modifier: Modifier, @StringRes nameStringRes: Int) {
    Text(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        text = stringResource(nameStringRes),
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(4.dp)
    )
}

@Composable
fun SubredditMembers(modifier: Modifier, @StringRes membersStringRes: Int) {
    Text(
        fontSize = 8.sp,
        text = stringResource(membersStringRes),
        color = Color.Gray,
        modifier = modifier
    )
}

@Composable
fun SubredditDescription(modifier: Modifier, @StringRes descriptionStringRes: Int) {
    Text(
        fontSize = 8.sp,
        text = stringResource(descriptionStringRes),
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(4.dp)
    )
}

@Composable
fun Community(text: String, modifier: Modifier = Modifier, onCommunityClicked: () -> Unit = {}) {
    Row(modifier = modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        .fillMaxWidth()
        .clickable { onCommunityClicked.invoke() }
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.subreddit_placeholder),
            contentDescription = stringResource(id = R.string.community_icon),
            modifier = modifier
                .size(24.dp)
                .clip(CircleShape)
        )
        Text(
            fontSize = 10.sp,
            color = MaterialTheme.colors.primaryVariant,
            text = text,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun Communities(modifier: Modifier = Modifier) {
    mainCommunities.forEach {
        Community(text = stringResource(it))
    }

    Spacer(modifier = modifier.height(4.dp))

    BackgroundText(stringResource(R.string.communities))

    communities.forEach {
        Community(text = stringResource(it))
    }
}

@Preview
@Composable
fun SubredditBodyPreview() {
    SubredditBody(SubredditModel.DEFAULT_SUBREDDIT)
}

@Preview
@Composable
fun SubredditPreview() {
    Subreddit(SubredditModel.DEFAULT_SUBREDDIT)
}

@Preview
@Composable
fun CommunityPreview() {
    Community(stringResource(id = R.string.raywenderlich_com))
}

@Preview
@Composable
fun CommunitiesPreview() {
    Column {
        Communities()
    }
}