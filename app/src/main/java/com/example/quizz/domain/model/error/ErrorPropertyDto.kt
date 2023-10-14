package pl.lsisoftware.cinema_domain.model.dto.error

import androidx.annotation.Keep

@Keep
data class ErrorPropertyDto(
    val fieldName: ErrorFieldDto?,
    val seats: List<ErrorSeat>?
)

@Keep
data class ErrorSeat(
    val screeningItemId: String,
    val code: Int,
    val message: String
)