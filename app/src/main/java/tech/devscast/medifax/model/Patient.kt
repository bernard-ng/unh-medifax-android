package tech.devscast.medifax.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Patient(
    val id: Int,
    val email: String,
    val roles: List<String>,
    val password: String,
    val phoneNumber: String,
    val createdAt: String,
    val updatedAt: String,
    val isVerified: Boolean,
    val subscription: Subscription,
    val appointments: List<Appointment>
)

@Serializable
enum class Subscription {
    @SerialName("free")
    FREE,

    @SerialName("basic")
    BASIC,

    @SerialName("premium")
    PREMIUM
}
