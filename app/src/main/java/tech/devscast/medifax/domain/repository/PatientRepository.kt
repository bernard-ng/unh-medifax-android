package tech.devscast.medifax.domain.repository

import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.domain.dto.Response

interface PatientRepository {
    suspend fun find(id: String): Response<Patient?>
    suspend fun register(email: String, password: String, fullName: String): Response<Patient?>
}