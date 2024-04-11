package tech.devscast.medifax.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class Specialization(
    val id: Int,
    val name: String,
    val description: String,
)
