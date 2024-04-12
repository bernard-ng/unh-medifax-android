package tech.devscast.medifax.data.repository

import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.domain.ApiService
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val api: ApiService
): PatientRepository {
    override suspend fun find(id: String): Patient? {
        val response = api.getPatient(id)
        return response.data
    }
}
