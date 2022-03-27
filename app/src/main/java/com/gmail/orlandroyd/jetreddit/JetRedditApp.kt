package com.gmail.orlandroyd.jetreddit


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.gmail.orlandroyd.jetreddit.appdrawer.AppDrawer
import com.gmail.orlandroyd.jetreddit.routing.JetRedditRouter
import com.gmail.orlandroyd.jetreddit.routing.Screen
import com.gmail.orlandroyd.jetreddit.screens.AddScreen
import com.gmail.orlandroyd.jetreddit.screens.HomeScreen
import com.gmail.orlandroyd.jetreddit.screens.MyProfileScreen
import com.gmail.orlandroyd.jetreddit.screens.SubredditsScreen
import com.gmail.orlandroyd.jetreddit.theme.JetRedditTheme
import com.gmail.orlandroyd.jetreddit.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun JetRedditApp(viewModel: MainViewModel) {
    JetRedditTheme {
        AppContent(viewModel)
    }
}

@Composable
private fun AppContent(viewModel: MainViewModel) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Crossfade(targetState = JetRedditRouter.currentScreen) { screenState: MutableState<Screen> ->

        Scaffold(
            topBar = getTopBar(screenState.value, scaffoldState, coroutineScope),
            drawerContent = {
                AppDrawer(
                    closeDrawerAction = { coroutineScope.launch { scaffoldState.drawerState.close() } }
                )
            },
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomNavigationComponent(screenState = screenState)
            },
            content = {
                MainScreenContainer(
                    modifier = Modifier.padding(bottom = 56.dp),
                    screenState = screenState,
                    viewModel = viewModel
                )
            }
        )
    }
}

fun getTopBar(
    screenState: Screen,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
): @Composable (() -> Unit) {
    if (screenState == Screen.MyProfile) {
        return {}
    } else {
        return { TopAppBar(scaffoldState = scaffoldState, coroutineScope = coroutineScope) }
    }
}

/**
 * Represents top app bar on the screen
 */
@Composable
fun TopAppBar(scaffoldState: ScaffoldState, coroutineScope: CoroutineScope) {

    val colors = MaterialTheme.colors

    TopAppBar(
        title = {
            Text(
                text = stringResource(JetRedditRouter.currentScreen.value.titleResId),
                color = colors.primaryVariant
            )
        },
        backgroundColor = colors.surface,
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch { scaffoldState.drawerState.open() }
            }) {
                Icon(
                    Icons.Filled.AccountCircle,
                    tint = Color.LightGray,
                    contentDescription = stringResource(id = R.string.account)
                )
            }
        }
    )
}

@Composable
private fun MainScreenContainer(
    modifier: Modifier = Modifier,
    screenState: MutableState<Screen>,
    viewModel: MainViewModel
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background
    ) {
        when (screenState.value) {
            Screen.Home -> HomeScreen(viewModel)
            Screen.Subscriptions -> SubredditsScreen()
            Screen.NewPost -> AddScreen()
            Screen.MyProfile -> MyProfileScreen(viewModel)
        }
    }
}

@Composable
private fun BottomNavigationComponent(
    modifier: Modifier = Modifier,
    screenState: MutableState<Screen>
) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        NavigationItem(0, R.drawable.ic_baseline_home_24, R.string.home_icon, Screen.Home),
        NavigationItem(
            1,
            R.drawable.ic_baseline_format_list_bulleted_24,
            R.string.subscriptions_icon,
            Screen.Subscriptions
        ),
        NavigationItem(2, R.drawable.ic_baseline_add_24, R.string.post_icon, Screen.NewPost),
    )
    BottomNavigation(modifier = modifier) {
        items.forEach {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = it.vectorResourceId),
                        contentDescription = stringResource(id = it.contentDescriptionResourceId)
                    )
                },
                selected = selectedItem == it.index,
                onClick = {
                    selectedItem = it.index
                    screenState.value = it.screen
                }
            )
        }
    }
}

private data class NavigationItem(
    val index: Int,
    val vectorResourceId: Int,
    val contentDescriptionResourceId: Int,
    val screen: Screen
)