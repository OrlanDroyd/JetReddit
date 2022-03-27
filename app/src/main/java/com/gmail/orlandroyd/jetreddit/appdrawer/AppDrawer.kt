package com.gmail.orlandroyd.jetreddit.appdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gmail.orlandroyd.jetreddit.R
import com.gmail.orlandroyd.jetreddit.theme.JetRedditThemeSettings

/**
 * Represents root composable for the app drawer used in screens
 */
@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    closeDrawerAction: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        AppDrawerHeader()

        AppDrawerBody(closeDrawerAction)

        AppDrawerFooter(modifier)
    }
}

/**
 * Represents app drawer header with the icon and the app name
 */
@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun AppDrawerHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            colorFilter = ColorFilter.tint(Color.LightGray),
            modifier = Modifier
                .padding(16.dp)
                .size(50.dp),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            contentDescription = stringResource(id = R.string.account)
        )

        Text(
            text = stringResource(R.string.default_username),
            color = MaterialTheme.colors.primaryVariant
        )

        ProfileInfo()

        Divider(
            color = MaterialTheme.colors.onSurface.copy(alpha = .2f),
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
        )
    }
}

@Composable
fun ProfileInfo(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        val (karmaItem, divider, ageItem) = createRefs()
        val colors = MaterialTheme.colors

        ProfileInfoItem(
            Icons.Filled.Star,
            R.string.default_karma_amount,
            R.string.karma,
            modifier = modifier.constrainAs(karmaItem) {
                centerVerticallyTo(parent)
                start.linkTo(parent.start)
            }
        )

        Divider(
            modifier = modifier
                .width(1.dp)
                .constrainAs(divider) {
                    centerVerticallyTo(karmaItem)
                    centerHorizontallyTo(parent)
                    height = Dimension.fillToConstraints
                },
            color = colors.onSurface.copy(alpha = .2f)
        )

        ProfileInfoItem(
            Icons.Filled.ShoppingCart,
            R.string.default_reddit_age_amount,
            R.string.reddit_age,
            modifier = modifier.constrainAs(ageItem) {
                start.linkTo(divider.end)
                centerVerticallyTo(parent)
            }
        )
    }
}

@Composable
private fun ProfileInfoItem(
    iconAsset: ImageVector,
    amountResourceId: Int,
    textResourceId: Int,
    modifier: Modifier
) {
    val colors = MaterialTheme.colors

    ConstraintLayout(modifier = modifier) {
        val (iconRef, amountRef, titleRef) = createRefs()

        val itemModifier =
            Modifier // as it’s good practice to differentiate between parent and item modifiers.

        Icon(
            contentDescription = stringResource(id = textResourceId),
            imageVector = iconAsset,
            tint = Color.Blue,
            modifier = itemModifier
                .constrainAs(iconRef) {
                    centerVerticallyTo(parent)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp)
        )

        Text(
            text = stringResource(amountResourceId),
            color = colors.primaryVariant,
            fontSize = 10.sp,
            modifier = itemModifier
                .padding(start = 8.dp)
                .constrainAs(amountRef) {
                    top.linkTo(iconRef.top)
                    start.linkTo(iconRef.end)
                    bottom.linkTo(titleRef.top)
                }
        )

        Text(
            text = stringResource(textResourceId),
            color = Color.Gray,
            fontSize = 10.sp,
            modifier = itemModifier
                .padding(start = 8.dp)
                .constrainAs(titleRef) {
                    top.linkTo(amountRef.bottom)
                    start.linkTo(iconRef.end)
                    bottom.linkTo(iconRef.bottom)
                }
        )
    }
}

/**
 * Represents app drawer actions:
 * * screen navigation
 * * app light/dark mode
 */
@Composable
private fun AppDrawerBody(closeDrawerAction: () -> Unit) {
    Column {
        ScreenNavigationButton(
            icon = Icons.Filled.AccountBox,
            label = stringResource(R.string.my_profile),
            onClickAction = {
                closeDrawerAction
            }
        )
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = stringResource(R.string.home),
            onClickAction = {
                closeDrawerAction
            }
        )
    }
}

/**
 * Represents component in the app drawer that the user can use to change the screen
 */
@Composable
private fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()

    Surface(
        modifier = surfaceModifier,
        color = colors.surface,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = onClickAction,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    imageVector = icon,
                    colorFilter = ColorFilter.tint(Color.Gray),
                    contentDescription = label
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    fontSize = 10.sp,
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = colors.primaryVariant
                )
            }
        }
    }
}

/**
 * Represents setting component in the app drawer
 */
@Composable
private fun AppDrawerFooter(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                bottom = 16.dp,
                end = 16.dp
            )
    ) {

        val colors = MaterialTheme.colors
        val (settingsImage, settingsText, darkModeButton) = createRefs()

        Icon(
            modifier = modifier.constrainAs(settingsImage) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(id = R.string.settings),
            tint = colors.primaryVariant
        )

        Text(
            fontSize = 10.sp,
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.body2,
            color = colors.primaryVariant,
            modifier = modifier
                .padding(start = 16.dp)
                .constrainAs(settingsText) {
                    start.linkTo(settingsImage.end)
                    centerVerticallyTo(settingsImage)
                }
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_moon),
            contentDescription = stringResource(id = R.string.change_theme),
            modifier = modifier
                .clickable(onClick = { changeTheme() })
                .constrainAs(darkModeButton) {
                    end.linkTo(parent.end)
                    bottom.linkTo(settingsImage.bottom)
                },
            tint = colors.primaryVariant
        )
    }
}

private fun changeTheme() {
    JetRedditThemeSettings.isInDarkTheme.value = JetRedditThemeSettings.isInDarkTheme.value.not()
}

@Preview
@Composable
private fun ProfileInfoItemPreview() {
    ProfileInfoItem(
        Icons.Filled.ShoppingCart,
        R.string.default_reddit_age_amount,
        R.string.reddit_age,
        Modifier
    )
}
