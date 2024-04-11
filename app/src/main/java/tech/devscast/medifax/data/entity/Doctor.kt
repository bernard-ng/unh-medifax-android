package tech.devscast.medifax.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Doctor(
    val id: Int,
    val email: String,
    val fullName: String,
    val phoneNumber: String,
    val isAvailable: Boolean,
    val specialization: Specialization,
    val profileImage: String? = null
)
