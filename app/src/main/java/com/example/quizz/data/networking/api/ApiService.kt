package com.example.quizz.data.networking.api

import com.example.quizz.data.dto.QuestionsDto
import com.example.quizz.data.dto.QuizText
import com.example.quizz.data.dto.QuizTopic
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/quiz/generateByTopic")
    suspend fun genreByTopic(@Body topic: QuizTopic): Response<QuestionsDto>

    @POST("api/v1/quiz/generateByText")
    suspend fun genreByText(@Body topic: QuizText): Response<QuestionsDto>
}