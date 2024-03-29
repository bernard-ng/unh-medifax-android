package tech.devscast.medifax.model

import kotlinx.serialization.Serializable

@Serializable
data class Doctor(
    val id: Int,
    val email: String,
    val roles: List<String>,
    val password: String,
    val fullName: String,
    val phoneNumber: String,
    val createdAt: String,
    val updatedAt: String,
    val isVerified: Boolean,
    val isAvailable: Boolean,
    val appointments: List<Appointment>,
    val specialization: Specialization
)
