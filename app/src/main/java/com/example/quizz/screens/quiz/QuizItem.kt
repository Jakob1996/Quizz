package com.example.quizz.screens.quiz

data class QuizItem(
    val name: String,
    val questionsDescription: List<String>,
    val userAnswers: List<Int>,
    val correctAnswers: List<Int>,
)