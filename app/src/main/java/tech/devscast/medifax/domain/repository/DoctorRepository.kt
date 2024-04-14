package tech.devscast.medifax.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.domain.dto.Response

interface DoctorRepository {
    suspend fun findAll(): Flow<Response<List<Doctor>?>>;
    suspend fun find(id: String): Response<Doctor?>;
}