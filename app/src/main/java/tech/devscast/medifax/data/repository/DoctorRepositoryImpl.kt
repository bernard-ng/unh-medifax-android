package tech.devscast.medifax.data.repository

import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.domain.ApiService
import tech.devscast.medifax.domain.repository.DoctorRepository
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val api: ApiService
) : DoctorRepository {
    override suspend fun findAll(): List<Doctor> {
        val response = api.getDoctors()

        if (response.success) return response.data!!
        return emptyList()
    }

    override suspend fun find(id: String): Doctor? {
        val response = api.getDoctor(id)
        return response.data
    }
}
