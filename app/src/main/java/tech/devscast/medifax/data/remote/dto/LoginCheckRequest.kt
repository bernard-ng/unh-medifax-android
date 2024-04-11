package tech.devscast.medifax.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginCheckRequest(
    val username: String,
    val password: String
)
