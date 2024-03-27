package tech.devscast.medifax.model.assignment

import kotlinx.serialization.Serializable

@Serializable
data class Teacher(
    val id: String,
    val url: String,
    val givenName: String,
    val surName: String,
    val initials: String,
    val displayName: String,
    val mail: String,
    val office: String,
    val telephoneNumber: String,
    val mobileNumber: String,
    val photo: String,
    val department: String,
    val title: String,
    val personalTitle: String,
    val affiliations: List<String>,
    val updatedAt: String,
    val uid: String,
    val employeeId: String
)
