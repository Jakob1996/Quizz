package com.example.quizz.ui.theme.screens.main

import androidx.lifecycle.ViewModel
import com.example.quizz.domain.useCases.QuizUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(var quizUseCase: QuizUseCase) : ViewModel() {


}