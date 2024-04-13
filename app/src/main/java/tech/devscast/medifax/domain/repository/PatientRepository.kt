package tech.devscast.medifax.domain.repository

import tech.devscast.medifax.data.entity.Patient

interface PatientRepository {
    suspend fun find(id: String): Patient?
    suspend fun register(email: String, password: String, fullName: String): Patient?
}