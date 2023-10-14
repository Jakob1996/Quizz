package com.example.quizz.data.config

import com.example.quizz.domain.model.resource.Resource
import com.example.quizz.domain.model.resource.ResourceError
import com.example.quizz.data.extensions.retrofit.toResource
import retrofit2.Response

suspend fun <T : Any> handleRequest(requestFunc: suspend () -> Response<T>): Resource<T> {
    return try {
        requestFunc().toResource()
    } catch (e: Exception) {
        Resource.error(ResourceError.GenericError)
    }
}