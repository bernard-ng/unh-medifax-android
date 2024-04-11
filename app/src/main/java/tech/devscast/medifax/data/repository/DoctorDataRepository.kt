package tech.devscast.medifax.data.repository

import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.data.remote.ApiService
import javax.inject.Inject

class DoctorDataRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun findAll(): List<Doctor> {
        val response = api.getDoctors()

        if (response.success) return response.data!!
        return emptyList()
    }

    suspend fun find(id: String): Doctor? {
        val response = api.getDoctor(id)
        return response.data
    }
}
