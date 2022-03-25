package com.gmail.orlandroyd.jetreddit.appdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
private fun AppDrawerHeader() {
    //TODO add your code here
}

@Composable
fun ProfileInfo() {
    //TODO add your code here
}

@Composable
private fun ProfileInfoItem(
    iconAsset: ImageVector,
    amountResourceId: Int,
    textResourceId: Int,
    modifier: Modifier
) {
    //TODO add your code here
}

/**
 * Represents app drawer actions:
 * * screen navigation
 * * app light/dark mode
 */
@Composable
private fun AppDrawerBody(closeDrawerAction: () -> Unit) {
    //TODO add your code here
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
    //TODO add your code here
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
