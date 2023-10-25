package com.example.quizz.di.viewModel

import com.example.quizz.domain.useCases.QuizUseCase
import com.example.quizz.screens.chat.ChatViewModel
import com.example.quizz.screens.quiz.QuizViewModel
import com.example.quizz.ui.theme.screens.main.MainActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMainActivityViewModel(quizUseCase: QuizUseCase): MainActivityViewModel {
        return MainActivityViewModel(quizUseCase)
    }

    @Provides
    @ViewModelScoped
    fun provideChatViewModel(): ChatViewModel {
        return ChatViewModel()
    }
    @Provides
    @ViewModelScoped
    fun provideQuizScreenViewModel(quizUseCase: QuizUseCase): QuizViewModel {
        return QuizViewModel(quizUseCase)
    }
}