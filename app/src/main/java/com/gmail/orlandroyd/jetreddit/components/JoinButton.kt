package com.gmail.orlandroyd.jetreddit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun JoinButton(onClick: (Boolean) -> Unit = {}) {

    var buttonState: JoinButtonState
            by remember { mutableStateOf(JoinButtonState.IDLE) }

    // Button shape
    val shape = RoundedCornerShape(corner = CornerSize(12.dp))

    // Button background
    val buttonBackgroundColor: Color =
        if (buttonState == JoinButtonState.PRESSED)
            Color.White
        else
            Color.Blue

    // Button icon
    val iconAsset: ImageVector =
        if (buttonState == JoinButtonState.PRESSED)
            Icons.Default.Check
        else
            Icons.Default.Add
    val iconTintColor: Color =
        if (buttonState == JoinButtonState.PRESSED)
            Color.Blue
        else
            Color.White
    Box(
        modifier = Modifier
            .clip(shape)
            .border(width = 1.dp, color = Color.Blue, shape = shape)
            .background(color = buttonBackgroundColor)
            .size(width = 40.dp, height = 24.dp)
            .clickable(onClick = {
                buttonState =
                    if (buttonState == JoinButtonState.IDLE) {
                        onClick.invoke(true)
                        JoinButtonState.PRESSED
                    } else {
                        onClick.invoke(false)
                        JoinButtonState.IDLE
                    }
            }),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = iconAsset,
            contentDescription = "Plus Icon",
            tint = iconTintColor,
            modifier = Modifier.size(16.dp)
        )
    }
}

enum class JoinButtonState {
    IDLE,
    PRESSED
}

@Preview
@Composable
fun JoinButtonPreview() {
    JoinButton(onClick = {})
}