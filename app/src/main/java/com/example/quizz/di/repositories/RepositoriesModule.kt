package com.example.quizz.di.repositories

import com.example.quizz.data.networking.api.ApiService
import com.example.quizz.data.repositories.UserRepositoryImpl
import com.example.quizz.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository{
        return UserRepositoryImpl(apiService)
    }
}