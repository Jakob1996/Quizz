package com.example.quizz.data.repositories

import com.example.quizz.data.config.handleRequest
import com.example.quizz.domain.repositories.UserRepository
import com.example.quizz.data.dto.QuestionsDto
import com.example.quizz.data.networking.api.ApiService
import com.example.quizz.domain.model.resource.Resource

class UserRepositoryImpl(private val apiService: ApiService): UserRepository {

    override suspend fun getQuestions(): Resource<QuestionsDto> {

        return handleRequest { apiService.fetchQuestions() }
    }
}