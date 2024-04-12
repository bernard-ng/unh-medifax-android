package tech.devscast.medifax.data.repository

import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.domain.ApiService
import tech.devscast.medifax.domain.dto.CreateAppointmentRequest
import tech.devscast.medifax.domain.repository.AppointmentRepository
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val api: ApiService
): AppointmentRepository {
    override suspend fun createAppointment(patientId: String, doctorId: String, description: String, date: String): Boolean {
        val data = CreateAppointmentRequest(
            patient = CreateAppointmentRequest.Identifier(patientId),
            doctor = CreateAppointmentRequest.Identifier(doctorId),
            description = description,
            date = date
        )

        val response = api.createAppointment(data)
        return response.success
    }

    override suspend fun findForPatient(patientId: String): List<Appointment> {
        val response = api.getPatientAppointments(patientId)

        if (response.success) return response.data!!
        return emptyList()
    }
}
