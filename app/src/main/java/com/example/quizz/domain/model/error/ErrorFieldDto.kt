package pl.lsisoftware.cinema_domain.model.dto.error

import androidx.annotation.Keep

@Keep
data class ErrorFieldDto(
    val code: Int?,
    val message: String?
)