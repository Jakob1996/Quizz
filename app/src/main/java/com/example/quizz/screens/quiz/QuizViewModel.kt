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

    private val _playerResults: MutableStateFlow<QuestionsDto?> = MutableStateFlow(null)
    private val playerResults = _playerResults.asStateFlow()

    fun setCheckResult(currentState: QuestionsDto) {
        _playerResults.value = currentState
    }

    fun getUserResult(): String {
        val questionsSize = playerResults.value?.questions?.size
        var correctAnswers = 0
        playerResults.value?.questions?.forEach { questionDto ->
            questionDto.answers.forEach {
                if (it.isValid) {
                    if (it.isValid == it.userChoose) {
                        correctAnswers += 1
                    }
                }
            }
        }

        return "$correctAnswers / $questionsSize"
    }


    private val _questionsDto: MutableStateFlow<QuestionsDto?> = MutableStateFlow(null)
    val questionsDto = _questionsDto.asStateFlow()

    fun resetUiState() {
        _uiState.value = null
    }

    fun genreQuiz(topic: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val quiz = quizUseCase.genreQuizByTopic(topic)
            if (quiz.isSuccess()) {
                _questionsDto.value = quiz.value
                _uiState.value = UiState.Success(quiz.status.name)
            } else {
                _uiState.value = UiState.Error(quiz.status.name)
                Log.d("Error", "Something wrong ${quiz.error}")
            }
        }
    }

    fun createQuizByText(text: String){
        CoroutineScope(Dispatchers.IO).launch {
            val quiz = quizUseCase.genreQuizByText(text)
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