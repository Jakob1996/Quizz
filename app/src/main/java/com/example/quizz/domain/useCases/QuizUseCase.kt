package com.example.quizz.domain.useCases

import com.example.quizz.data.dto.QuestionsDto
import com.example.quizz.domain.model.resource.Resource
import com.example.quizz.domain.repositories.QuizRepository
import javax.inject.Inject


class QuizUseCase @Inject constructor(private val quizRepository: QuizRepository) {

    suspend fun genreQuizByTopic(topic: String): Resource<QuestionsDto> {
        return quizRepository.genreQuizByTopic(topic)
    }

    suspend fun genreQuizByText(topic: String): Resource<QuestionsDto> {
        return quizRepository.genreQuizByTopic(topic)
    }
}