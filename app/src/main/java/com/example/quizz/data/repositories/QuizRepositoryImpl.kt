package com.example.quizz.data.repositories

import com.example.quizz.data.config.handleRequest
import com.example.quizz.domain.repositories.QuizRepository
import com.example.quizz.data.dto.QuestionsDto
import com.example.quizz.data.networking.api.ApiService
import com.example.quizz.domain.model.resource.Resource
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(private val apiService: ApiService) : QuizRepository {

    override suspend fun getQuestions(): Resource<QuestionsDto> {

        return handleRequest { apiService.fetchQuestions() }
    }
}