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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    var topic by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(), // Wypełnia całą dostępną przestrzeń
        verticalArrangement = Arrangement.Top, // Układa zawartość na górze
        horizontalAlignment = Alignment.CenterHorizontally, // Wyrównuje zawartość do lewej (opcjonalnie)
    ) {
        Text(
            text = "Witaj w naszym Quizzie", Modifier.padding(top = 30.dp)
        )

        TopicTextField(text = topic, onValueChange = { top -> topic = top }, textLabel = "")

        GenreButton({})
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            contentAlignment = Alignment.Center) {
            QuestionResponseText("Jaki jest nawiększy kanion w europie?")
        }

        Buttons({}, {}, {}, {})
    }
}

@Composable
fun QuestionResponseText(text: String) {
    Text(text = text)
}

@Composable
fun GenreButton(onClick: () -> Unit) {
    Button(modifier = Modifier
        .width(150.dp)
        .height(90.dp).padding(10.dp).padding(top = 20.dp), onClick = onClick, ){
        Text(text = "Genre quiz")
    }
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
        label = { Text("Temat") },
        leadingIcon = {
            val emailIcon = Icons.Filled.Add
            Icon(imageVector = emailIcon, contentDescription = null)
        }
    )
}

@Composable
fun Buttons(
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
fun MainScreenPreview() {
    MainScreen()
}