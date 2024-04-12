package tech.devscast.medifax.domain.repository

import tech.devscast.medifax.data.entity.Doctor

interface DoctorRepository {
    suspend fun findAll(): List<Doctor>;
    suspend fun find(id: String): Doctor?;
}