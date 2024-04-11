package tech.devscast.medifax.data.repository

import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.data.remote.ApiService
import javax.inject.Inject

class PatientDataRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun find(id: String): Patient? {
        val response = api.getPatient(id)
        return response.data
    }
}
