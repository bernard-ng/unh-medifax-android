package tech.devscast.medifax.model.assignment

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleCollection(
    val title: String,
    val data: List<ScheduleItem>,
    val numberOfDays: Int,
    val start: String,
    val teachers: List<Teacher>,
    val includeDeleted: Boolean,
    val latestUpdate: String
)
