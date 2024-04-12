package tech.devscast.medifax.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginCheckRequest(
    val username: String,
    val password: String
)
