package com.example.quizz.domain.model.error

import androidx.annotation.Keep

@Keep
data class ErrorDto(
    val error: ErrorDetailsDto?
)