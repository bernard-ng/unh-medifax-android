package tech.devscast.medifax.domain.repository

import tech.devscast.medifax.data.entity.Appointment

interface AppointmentRepository {
    suspend fun createAppointment(patientId: String, doctorId: String, description: String, date: String): Boolean;
    suspend fun findForPatient(patientId: String): List<Appointment>;
}