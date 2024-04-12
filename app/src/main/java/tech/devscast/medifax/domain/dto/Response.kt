package tech.devscast.medifax.domain.dto

data class Response<T>(
    val data: T,
    val success: Boolean,
    val code: Int? = null,
    val description: String? = null,
)
