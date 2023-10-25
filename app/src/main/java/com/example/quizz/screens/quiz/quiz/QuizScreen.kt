package com.example.quizz.screens.quiz.quiz

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizz.data.dto.AnswerDto
import com.example.quizz.data.dto.QuestionsDto
import com.example.quizz.data.dto.getData
import com.example.quizz.screens.quiz.QuizViewModel
import com.example.quizz.ui.theme.ChatLightGreenColor
import com.example.quizz.ui.theme.Dark500
import com.example.quizz.ui.theme.Purple80
import com.example.quizz.ui.theme.PurpleGrey40
import com.example.quizz.ui.theme.QuizDarkGreenColor

@Composable
fun QuizScreen(quizViewModel: QuizViewModel) {

//    val exampleQuestions = quizViewModel.questionsDto.collectAsState().value

    val progress by quizViewModel.progressState.collectAsState()

    val exampleQuestions = getData()

    val answersForCurrent = exampleQuestions!!.questions[progress - 1].answers.map { it.answerText }
    val currentAnswers = exampleQuestions!!.questions[progress - 1].answers

    var clicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProgressBar(progress, exampleQuestions.questions.size)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, top = 70.dp)
                .padding(start = 10.dp, end = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            QuestionResponseText(exampleQuestions.questions[progress - 1].questionText ?: "")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .padding(start = 10.dp, end = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            AnswerTextList(answersForCurrent)
        }


        Spacer(modifier = Modifier.weight(1f))  // Spacer nad QuizButtons

        Buttons(currentAnswers, onClick = { clicked = !clicked }, isClicked = clicked)

        Spacer(modifier = Modifier.weight(1f))

        QuizBottomPanel(
            {
                if (progress > 1) {
                    quizViewModel.changeProgress(-1)
                }
            },
            {
                if (progress < exampleQuestions.questions.size) {
                    quizViewModel.changeProgress(1)
                }
            },
        )
    }
}


@Composable
fun Buttons(answers: List<AnswerDto>, onClick: () -> Unit, isClicked: Boolean) {
    val state = rememberLazyGridState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Dark500)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), state = state, content = {
            itemsIndexed(answers) { index, value ->
                OutlinedButton(
                    modifier = Modifier
                        .width(130.dp)
                        .height(70.dp)
                        .padding(5.dp),
                    onClick = {
                        onClick()
                        answers.forEach { it.userChoose = false }
                        value.userChoose = true
                    },
                    border = if (isClicked) {
                        if (value.userChoose) {
                            BorderStroke(2.dp, Color.Green)
                        } else {
                            BorderStroke(2.dp, Color.White)
                        }
                    } else {
                        BorderStroke(2.dp, Color.White)
                    },
                ) {
                    Text("${index + 1}", color = Color.White)
                }
            }
        })
    }
}

@Composable
fun QuestionResponseText(text: String) {
    Text(text = text, textAlign = TextAlign.Start)
}

@Composable
fun AnswerTextList(answers: List<String>) {
    LazyColumn() {
        itemsIndexed(answers) { index, text ->
            Text(text = "${index + 1}.$text", textAlign = TextAlign.Start)
        }
    }
}

@Composable
fun AnswerText(answerText: String) {
    Text(text = answerText, textAlign = TextAlign.Start)
}

@Composable
fun ProgressBar(state: Int, questions: Int) {

    val size by animateFloatAsState(
        targetValue = state / questions.toFloat(),
        tween(durationMillis = 500, delayMillis = 100, easing = LinearOutSlowInEasing)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 30.dp, end = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .widthIn(min = 30.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(text = "$state/$questions")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(17.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(9.dp))
                    .background(
                        PurpleGrey40
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(size)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(9.dp))
                    .background(
                        Purple80
                    )
                    .animateContentSize()
            )
        }
    }
}

@Composable
fun QuizBottomPanel(previousButtonAction: () -> Unit, nextButtonAction: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 30.dp, end = 30.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(modifier = Modifier.width(150.dp), onClick = previousButtonAction) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Previous",
                    tint = Color.White
                )
                Text(modifier = Modifier.padding(10.dp), text = "Previous", color = Color.White)
            }
        }

        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(modifier = Modifier.width(150.dp), onClick = nextButtonAction) {
                Text(modifier = Modifier.padding(10.dp), text = "Next", color = Color.White)
                Icon(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = "Previous",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun QuizButtons(
    firstButtonClick: () -> Unit,
    secondButtonClick: () -> Unit,
    thirdButtonClick: () -> Unit,
    fourthButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)
                    .padding(5.dp), onClick = firstButtonClick
            ) {
                Text("1", color = Color.White)
            }

            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)
                    .padding(5.dp), onClick = secondButtonClick
            ) {
                Text("2", color = Color.White)
            }
        }

        Row {
            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)
                    .padding(5.dp), onClick = thirdButtonClick
            ) {
                Text("3", color = Color.White)
            }

            OutlinedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)
                    .padding(5.dp), onClick = fourthButtonClick
            ) {
                Text("4", color = Color.White)
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun QuizScreenPreview() {
    AnswerTextList(listOf("fsdfsdf", "fsdfsd", "fsdfsdf", "fsdfsd"))
}