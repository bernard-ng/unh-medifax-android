package tech.devscast.medifax.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginCheckResponse(
    val token: String
)
