package com.example.quizz.domain.repositories

import com.example.quizz.data.dto.QuestionsDto
import com.example.quizz.domain.model.resource.Resource

interface QuizRepository {

    suspend fun getQuestions(): Resource<QuestionsDto>
}