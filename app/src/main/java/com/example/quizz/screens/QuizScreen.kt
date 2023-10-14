package com.example.quizz.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuizScreen() {

    Column(
        modifier = Modifier.fillMaxSize(), // Wypełnia całą dostępną przestrzeń
        verticalArrangement = Arrangement.Top, // Układa zawartość na górze
        horizontalAlignment = Alignment.CenterHorizontally, // Wyrównuje zawartość do lewej (opcjonalnie)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .padding(top = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            QuestionResponseText("What is the main goal of data science?")
        }

        Column() {
            AnswerText(answerText = "1.To extract insights and knowledge from data")
            AnswerText(answerText = "2.To develop software applications")
            AnswerText(answerText = "3.To design user interfaces")
            AnswerText(answerText = "4.To manage databases")
        }

        QuizButtons({}, {}, {}, {})
    }
}

@Composable
fun QuestionResponseText(text: String) {
    Text(text = text)
}

@Composable
fun AnswerText(answerText: String) {
    Text(text = answerText, textAlign = TextAlign.Center)
}

@Composable
fun QuizButtons(
    firstButtonClick: () -> Unit,
    secondButtonClick: () -> Unit,
    thirdButtonClick: () -> Unit,
    fourthButtonClick: () -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)
                    .padding(5.dp), onClick = firstButtonClick
            ) {
                Text("1")
            }

            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)
                    .padding(5.dp), onClick = secondButtonClick
            ) {
                Text("2")
            }
        }

        Row() {
            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)
                    .padding(5.dp), onClick = thirdButtonClick
            ) {
                Text("3")
            }

            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)
                    .padding(5.dp), onClick = fourthButtonClick
            ) {
                Text("4")
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen()
}
