package tech.devscast.medifax.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.domain.ApiService
import tech.devscast.medifax.domain.dto.Response
import tech.devscast.medifax.domain.repository.DoctorRepository
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val api: ApiService
) : DoctorRepository {
    override suspend fun findAll(): Flow<Response<List<Doctor>?>> {
        return flow {
            val response = api.getDoctors()
            emit(response)
        }
    }

    override suspend fun find(id: String): Response<Doctor?> {
        return api.getDoctor(id)
    }
}
