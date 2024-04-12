package tech.devscast.medifax.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginCheckResponse(
    val token: String
)
