package com.example.quizz.domain.model.error

import androidx.annotation.Keep
import pl.lsisoftware.cinema_domain.model.dto.error.ErrorPropertyDto

@Keep
data class ErrorDetailsDto(
    val code: ErrorCodeStatusDto?,
    val message: String?,
    val properties: ErrorPropertyDto?
)