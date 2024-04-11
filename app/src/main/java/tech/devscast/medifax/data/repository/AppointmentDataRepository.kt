package tech.devscast.medifax.data.repository

import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.data.remote.ApiService
import tech.devscast.medifax.data.remote.dto.CreateAppointmentRequest
import javax.inject.Inject

class AppointmentDataRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun createAppointment(patientId: String, doctorId: String, description: String, date: String): Boolean {
        val data = CreateAppointmentRequest(
            patient = CreateAppointmentRequest.Identifier(patientId),
            doctor = CreateAppointmentRequest.Identifier(doctorId),
            description = description,
            date = date
        )

        val response = api.createAppointment(data)
        return response.success
    }

    suspend fun findForPatient(patientId: String): List<Appointment> {
        val response = api.getPatientAppointments(patientId)

        if (response.success) return response.data!!
        return emptyList()
    }
}
