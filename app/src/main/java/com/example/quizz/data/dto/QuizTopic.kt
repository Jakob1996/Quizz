package com.example.quizz.data.dto

data class QuizTopic(
    val topic: String,
    val numberOfQuestions: Int = 10,
    val numberOfOptions: Int = 4,
    val maxQuestionSize: Int = 20,
    val maxResponseSize: Int = 10,
    val difficultyLevel: Int = 1
)