package tech.devscast.medifax.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val id: Int,
    val patient: Patient,
    val doctor: Doctor,
    val createdAt: String,
    val updatedAt: String,
    val status: String,
    val description: String,
    val date: String
)

@Serializable
enum class AppointmentStatus {
    @SerialName("pending")
    PENDING,

    @SerialName("approved")
    APPROVED,

    @SerialName("canceled")
    CANCELED
}
