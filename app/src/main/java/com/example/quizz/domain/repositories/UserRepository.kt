package com.example.quizz.domain.repositories

import com.example.quizz.data.dto.QuestionsDto
import com.example.quizz.domain.model.resource.Resource

interface UserRepository {

    suspend fun getQuestions(): Resource<QuestionsDto>
}