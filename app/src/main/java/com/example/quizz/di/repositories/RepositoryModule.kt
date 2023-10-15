package com.example.quizz.di.repositories

import com.example.quizz.data.networking.api.ApiService
import com.example.quizz.data.repositories.QuizRepositoryImpl
import com.example.quizz.domain.repositories.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): QuizRepository{
        return QuizRepositoryImpl(apiService)
    }
}