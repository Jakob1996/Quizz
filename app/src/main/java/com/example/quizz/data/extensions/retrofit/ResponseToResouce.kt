package com.example.quizz.data.extensions.retrofit

import com.example.quizz.domain.model.error.ErrorDto
import com.google.gson.Gson
import com.example.quizz.domain.model.resource.Resource
import com.example.quizz.domain.model.resource.ResourceError
import retrofit2.Response

const val ERROR_PARSING_MESSAGE = "Occur error on parsing API error"
const val ERROR_PARSING_CODE = -1

fun <T> Response<T>.toResource(): Resource<T> =
    if (isSuccessful) {
        Resource.success(body())
    } else {
        try {
            val json = errorBody()?.string().orEmpty()
            val errorDto = Gson().fromJson(json, ErrorDto::class.java)
            val resource = ResourceError.ApiError(
                code = errorDto.error?.code,
                details = errorDto.error?.message,
                httpCode = code(),
                error = errorDto
            )
            Resource.error<T>(resource)
        } catch (exception: Exception) {
            Resource.error<T>(ResourceError.GenericError)
        }
    }