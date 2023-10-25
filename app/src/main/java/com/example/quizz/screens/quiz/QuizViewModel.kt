package com.example.quizz.screens.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.quizz.data.dto.QuestionsDto
import com.example.quizz.domain.useCases.QuizUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val quizUseCase: QuizUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState.asStateFlow()

    private val _progressState: MutableStateFlow<Int> = MutableStateFlow(1)
    val progressState = _progressState.asStateFlow()

    fun changeProgress(state: Int) {
        _progressState.update { it + state }
    }

    private val _playerResults: MutableStateFlow<List<QuizTemplate>?> = MutableStateFlow(null)
    val playerResults = _progressState.asStateFlow()


    fun createTemplateForCurrentQuiz(quizList: List<QuizTemplate>) {
        _playerResults.value = quizList
    }

    private val _questionsDto: MutableStateFlow<QuestionsDto?> = MutableStateFlow(null)
    val questionsDto = _questionsDto.asStateFlow()

    fun resetUiState() {
        _uiState.value = null
    }

    fun genreQuiz(topic: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val quiz = quizUseCase.genreQuiz(topic)
            if (quiz.isSuccess()) {
                _questionsDto.value = quiz.value
                _uiState.value = UiState.Success(quiz.status.name)
            } else {
                _uiState.value = UiState.Error(quiz.status.name)
                Log.d("Error", "Something wrong ${quiz.error}")
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val successMessage: String) : UiState()
        data class Error(val errorMessage: String) : UiState()
        object Default : UiState()
    }
}