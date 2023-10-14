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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StartQuizScreen() {
    var topic by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(), // Wypełnia całą dostępną przestrzeń
        verticalArrangement = Arrangement.Top, // Układa zawartość na górze
        horizontalAlignment = Alignment.CenterHorizontally, // Wyrównuje zawartość do lewej (opcjonalnie)
    ) {

        Text(
            text = "Welcome on Quizzz", Modifier.padding(top = 30.dp)
        )

        TopicTextField(text = topic, onValueChange = { top -> topic = top }, textLabel = "")

        GenreButton({})

    }
}

@Composable
fun GenreButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .width(180.dp)
            .height(90.dp)
            .padding(10.dp)
            .padding(top = 20.dp),
        onClick = onClick,
    ) {
        Text(text = "Generate quiz")
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
    StartQuizScreen()
}