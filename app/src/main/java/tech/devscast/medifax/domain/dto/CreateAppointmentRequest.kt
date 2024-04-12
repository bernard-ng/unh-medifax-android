package tech.devscast.medifax.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateAppointmentRequest(
    val patient: Identifier,
    val doctor: Identifier,
    val date: String,
    val description: String
) {
    @Serializable
    data class Identifier(
        val id: String
    )
}
