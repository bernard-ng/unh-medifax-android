package tech.devscast.medifax.model

import kotlinx.serialization.Serializable

@Serializable
data class Specialization(
    val id: Int,
    val name: String,
    val description: String,
    val doctors: List<Doctor>
)
