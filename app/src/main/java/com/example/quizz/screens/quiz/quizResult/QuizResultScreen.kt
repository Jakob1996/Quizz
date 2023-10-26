package com.example.quizz.screens.quiz.quizResult

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizz.screens.quiz.QuizViewModel

@Composable
fun QuizResultScreen(quizViewModel: QuizViewModel) {

    val result = quizViewModel.getUserResult()

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(),  contentAlignment = Alignment.Center){
            Text("Your result: ")
        }

        Box(modifier = Modifier.fillMaxWidth(),  contentAlignment = Alignment.Center){
            Text(text = result)
        }
    }
}

@Preview
@Composable
fun QuizResultScreenPreview() {
//    QuizResultScreen()
}