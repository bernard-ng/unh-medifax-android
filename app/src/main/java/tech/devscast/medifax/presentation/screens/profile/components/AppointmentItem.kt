package tech.devscast.medifax.presentation.screens.profile.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import tech.devscast.medifax.data.entity.Appointment

@Composable
fun AppointmentItem(appointment: Appointment) {
    Text(appointment.doctor.fullName)
    Text(appointment.date)
    Text(appointment.description)
    Text(appointment.status)
}