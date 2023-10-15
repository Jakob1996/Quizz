package com.example.quizz.di.useCases

import com.example.quizz.domain.repositories.QuizRepository
import com.example.quizz.domain.useCases.QuizUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule{

    @Provides
    @Singleton
    fun provideQuizUseCase(quizRepository: QuizRepository): QuizUseCase {
        return QuizUseCase(quizRepository)
    }
}