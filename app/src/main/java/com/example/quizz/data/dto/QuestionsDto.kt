package com.example.quizz.data.dto

data class QuestionsDto(val questions: List<QuestionDto>)

data class QuestionDto(val questionText: String, val answers: List<AnswerDto>)

data class AnswerDto(val answerText: String, val isValid: Boolean)