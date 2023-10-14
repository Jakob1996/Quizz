package com.example.quizz.domain.model.resource

import com.example.quizz.domain.model.error.ErrorCodeStatusDto
import com.example.quizz.domain.model.error.ErrorDto

sealed class ResourceError {
    data class ApiError(
        val code: ErrorCodeStatusDto?,
        val details: String?,
        val httpCode: Int?,
        val error: ErrorDto? = null
    ) : ResourceError()

    data class SyncError(val error: String) : ResourceError()

    object GenericError : ResourceError()

    val message: String?
        get() = when (this) {
            is ApiError -> {

                if ((this.httpCode ?: 0) < SOAP_ERRORS_HTTP_CODE) {
                    val verifyMessage = error?.error?.properties?.seats?.firstOrNull { it.code == 111 }
                    verifyMessage?.message ?: details?.takeIf { it.isNotBlank() }
                } else {
                    null
                }
            }
            else -> null
        }

    companion object {
        const val SOAP_ERRORS_HTTP_CODE = 500
    }
}

fun ResourceError?.orGeneric(): ResourceError {
    return this ?: ResourceError.GenericError
}