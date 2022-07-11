package com.gmail.orlandroyd.jetreddit.screens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.orlandroyd.jetreddit.databinding.ActivityChatBinding
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.composeButton.setContent {
            MaterialTheme {
                ComposeButton { showToast() }
            }
        }
    }

    private fun showToast() {
        Toast.makeText(this, "Imaginary chat started!", Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun ComposeButton(onButtonClick: () -> Unit) {
    val buttonColors = buttonColors(
        backgroundColor = Color(0xFF006837),
        contentColor = Color.White
    )

    Button(
        onClick = onButtonClick,
        elevation = null,
        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
        contentPadding = PaddingValues(
            start = 32.dp,
            end = 32.dp
        ),
        colors = buttonColors,
        modifier = Modifier.height(48.dp)
    ) {
        Text(
            text = "Start chatting".uppercase(Locale.US),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
private fun ComposeButtonPreview() {
    ComposeButton { }
}