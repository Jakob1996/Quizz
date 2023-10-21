package com.example.quizz.screens.startScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizz.ui.theme.ChatLightGreenColor
import com.example.quizz.ui.theme.Dark500
import com.example.quizz.ui.theme.QuizDarkGreenColor

@Composable
fun StartScreen(clickable: (Int) -> Unit) {

    val menuItems = listOf(
        MenuItem("quiz", 0, "", "Quiz game"),
        MenuItem("chat", 1, "", "Chat with Bob")
    )
    val state = rememberLazyGridState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark500)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), state = state, content = {
            items(menuItems.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (menuItems[index].name == "quiz") QuizDarkGreenColor else ChatLightGreenColor)
                        .clickable {
                            clickable(index)
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = menuItems.get(index).description, color = Color.White)
                }
            }
        })
    }
}

@Preview
@Composable
fun StartScreenPreview() {
}