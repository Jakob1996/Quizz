package com.example.quizz.domain.model.error

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
enum class ErrorCodeStatusDto(val status: Int) {
    @SerializedName("27")
    SCREEN_SWAPPING(27),

    @SerializedName("28")
    SCREENING_DELETED(28),

    @SerializedName("2")
    BASKET_EXPIRED(2),

    @SerializedName("20401")
    EMAIL_EXIST(20401);

    fun isScreenError(): Boolean {
        return when (this) {
            SCREENING_DELETED, SCREEN_SWAPPING -> true
            else -> false
        }
    }
}