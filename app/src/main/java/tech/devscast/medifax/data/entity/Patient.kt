package tech.devscast.medifax.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Patient(
    val id: Int,
    val email: String,
    val fullName: String,
    val phoneNumber: String = "",
    val subscription: Subscription,
    val profileImage: String? = null
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
