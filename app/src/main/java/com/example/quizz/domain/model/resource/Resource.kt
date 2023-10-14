package com.example.quizz.domain.model.resource

import pl.lsisoftware.cinema_domain.model.resource.ResourceStatus

data class Resource<out ResourceType>(
    val status: ResourceStatus,
    val value: ResourceType?,
    val error: ResourceError?,
    val cache: Boolean = false
) {

    fun isSuccess(): Boolean =
        status == ResourceStatus.Success

    fun isError(): Boolean =
        status == ResourceStatus.Error

    fun isLoading(): Boolean =
        status == ResourceStatus.Loading

    companion object {
        fun <Type> loading(): Resource<Type> =
            Resource(
                ResourceStatus.Loading,
                null,
                null
            )

        fun <Type> error(error: ResourceError): Resource<Type> =
            Resource(
                ResourceStatus.Error,
                null,
                error
            )

        fun <Type> success(value: Type? = null, cache: Boolean = false): Resource<Type> =
            Resource(ResourceStatus.Success, value, null, cache)

        fun from(vararg resources: Resource<*>): Resource<Void> =
            when {
                resources.any { it.isError() } -> error(ResourceError.GenericError)
                resources.any { it.isLoading() } -> loading()
                resources.all { it.isSuccess() } -> success()
                else -> error(ResourceError.GenericError)
            }

        fun <From, To> changeType(resource: Resource<From>, value: To?): Resource<To> =
            when {
                resource.isLoading() -> loading()
                resource.isSuccess() -> success(value, resource.cache)
                else -> error(resource.error.orGeneric())
            }
    }
}