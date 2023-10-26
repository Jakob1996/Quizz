package com.example.quizz.screens.quiz.generateQuiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizz.screens.quiz.QuizViewModel
import com.example.quizz.ui.theme.Purple40

@Composable
fun GenreQuizScreen(moveToQuiz: () -> Unit, quizViewModel: QuizViewModel) {
    val (isLoaderVisible, setLoaderVisible) = remember { mutableStateOf(false) }
    val (isGenreButtonVisible, setGenreButtonVisible) = remember { mutableStateOf(true) }

    var topic by remember {
        mutableStateOf("")
    }
    val uiState by quizViewModel.uiState.collectAsState()

    uiState?.let { CheckState(it, moveToQuiz) { quizViewModel.resetUiState() } }

    Column(
        modifier = Modifier.fillMaxSize(), // Wypełnia całą dostępną przestrzeń
        verticalArrangement = Arrangement.Top, // Układa zawartość na górze
        horizontalAlignment = Alignment.CenterHorizontally, // Wyrównuje zawartość do lewej (opcjonalnie)
    ) {

        Text(
            text = "Genre your quiz", Modifier.padding(top = 30.dp)
        )

        TopicTextField(text = topic, onValueChange = { top -> topic = top }, textLabel = "")

        GenreButton({
            if (topic.length > 1) {
                quizViewModel.genreQuiz(topic)
                setLoaderVisible(true)
                setGenreButtonVisible(false)
            }
        }, isGenreButtonVisible)

        Loader(isLoaderVisible)
    }
}

@Composable
fun CheckState(uiState: QuizViewModel.UiState, moveToQuiz: () -> Unit, clearUiState: () -> Unit) {
    when (uiState) {
        is QuizViewModel.UiState.Loading -> {

        }

        is QuizViewModel.UiState.Success -> {
            moveToQuiz()
            clearUiState()
        }

        is QuizViewModel.UiState.Error -> {

        }

        is QuizViewModel.UiState.Default -> {

        }
    }
}

@Composable
fun GenreButton(onClick: () -> Unit, isButtonVisible: Boolean) {
    Button(
        modifier = Modifier
            .width(180.dp)
            .height(90.dp)
            .padding(10.dp)
            .padding(top = 20.dp)
            .graphicsLayer(alpha = if (isButtonVisible) 1f else 0f),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            Purple40// kolor tła przycisku
        )
    ) {
        Text(text = "Generate quiz", color = Color.White)
    }
}

@Composable
fun Loader(isLoaderVisible: Boolean) {
    if (isLoaderVisible) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Loading your quiz...\nThis might take a moment",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(30.dp),
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestFun() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Moja aplikacja") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Akcja */ }) {
                Icon(Icons.Default.Add, contentDescription = "Dodaj")
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text("Treść główna")
            }
        }
    )
}

@Composable
fun TopicTextField(text: String, onValueChange: (String) -> Unit, textLabel: String) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 25.dp),
        value = text,
        onValueChange = onValueChange,
        label = { Text("Your topic") },
        leadingIcon = {
            val emailIcon = Icons.Filled.Add
            Icon(imageVector = emailIcon, contentDescription = null)
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainScreenPreview() {
    Loader(true)
}