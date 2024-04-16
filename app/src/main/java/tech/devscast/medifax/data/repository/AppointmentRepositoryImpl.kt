package tech.devscast.medifax.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.domain.ApiService
import tech.devscast.medifax.domain.dto.CreateAppointmentRequest
import tech.devscast.medifax.domain.dto.Response
import tech.devscast.medifax.domain.repository.AppointmentRepository
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val api: ApiService
): AppointmentRepository {
    override suspend fun createAppointment(
        doctorId: String,
        description: String,
        date: String
    ): Response<Appointment?> {
        val data = CreateAppointmentRequest(
            doctor = CreateAppointmentRequest.Identifier(doctorId),
            description = description,
            date = date
        )

        return api.createAppointment(data)
    }

    override fun findForPatient(): Flow<Response<List<Appointment>?>> {
        return flow {
            val response = api.getPatientAppointments()
            emit(response)
        }
    }
}
