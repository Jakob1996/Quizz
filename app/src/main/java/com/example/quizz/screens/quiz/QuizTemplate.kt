package com.example.quizz.screens.quiz

data class QuizTemplate(val answersOptions: List<OptionTemplate>)

data class OptionTemplate(
    val chooseByUser: Boolean = false,
    val isValid: Boolean,
    val answerDescription: String
)
