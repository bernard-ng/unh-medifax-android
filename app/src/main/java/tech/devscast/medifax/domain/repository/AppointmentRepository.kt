package tech.devscast.medifax.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.domain.dto.Response

interface AppointmentRepository {
    suspend fun createAppointment(patientId: String, doctorId: String, description: String, date: String): Response<Appointment?>
    fun findForPatient(patientId: String): Flow<Response<List<Appointment>?>>
}