package tech.devscast.medifax.data.remote.dto

data class Response<T>(
    val data: T,
    val success: Boolean,
    val code: Int? = null,
    val description: String? = null,
)
