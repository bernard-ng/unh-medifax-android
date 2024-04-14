package tech.devscast.medifax.data.repository

import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.domain.ApiService
import tech.devscast.medifax.domain.dto.RegisterRequest
import tech.devscast.medifax.domain.dto.Response
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val api: ApiService
): PatientRepository {
    override suspend fun find(id: String): Response<Patient?> {
        return api.getPatient(id)
    }

    override suspend fun register(email: String, password: String, fullName: String): Response<Patient?> {
        val data = RegisterRequest(email, password, fullName)
        return api.register(data)
    }
}
