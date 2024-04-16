package tech.devscast.medifax.domain

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.data.remote.ApiServiceImpl
import tech.devscast.medifax.domain.dto.CreateAppointmentRequest
import tech.devscast.medifax.domain.dto.LoginCheckRequest
import tech.devscast.medifax.domain.dto.LoginCheckResponse
import tech.devscast.medifax.domain.dto.RegisterRequest
import tech.devscast.medifax.domain.dto.Response

interface ApiService {

    suspend fun getDoctor(id: String): Response<Doctor?>
    suspend fun getDoctors(): Response<List<Doctor>?>
    suspend fun getPatient(id: String): Response<Patient?>
    suspend fun getPatientAppointments(): Response<List<Appointment>?>
    suspend fun createAppointment(data: CreateAppointmentRequest): Response<Appointment?>
    suspend fun login(data: LoginCheckRequest): Response<LoginCheckResponse?>
    suspend fun register(data: RegisterRequest): Response<Patient?>
    suspend fun me(): Response<Patient?>
}