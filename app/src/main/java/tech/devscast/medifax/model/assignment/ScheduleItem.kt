package tech.devscast.medifax.model.assignment

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleItem(
    val classes: List<String>,
    val room: String,
    val subject: String,
    val teacherAbbreviation: String,
    val start: String,
    val end: String,
    val uid: String,
    val hoursMask: Int,
    val description: String,
    val updatedAt: String
)
