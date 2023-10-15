package com.example.quizz.domain.useCases

import com.example.quizz.domain.repositories.QuizRepository
import javax.inject.Inject


class QuizUseCase @Inject constructor(val quizRepository: QuizRepository) {

}