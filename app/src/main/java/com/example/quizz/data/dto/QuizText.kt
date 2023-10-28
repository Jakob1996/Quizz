package com.example.quizz.data.dto

data class QuizText(
    val text: String,
    val numberOfQuestions: Int = 10,
    val numberOfOptions: Int = 4,
    val maxQuestionSize: Int = 20,
    val maxResponseSize: Int = 10,
    val difficultyLevel: Int = 1
)