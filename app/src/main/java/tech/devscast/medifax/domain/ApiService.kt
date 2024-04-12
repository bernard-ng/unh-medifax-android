package tech.devscast.medifax.domain

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
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
import tech.devscast.medifax.domain.dto.Response

interface ApiService {

    suspend fun getDoctor(id: String): Response<Doctor?>
    suspend fun getDoctors(): Response<List<Doctor>?>
    suspend fun getPatient(id: String): Response<Patient?>
    suspend fun getPatientAppointments(id: String): Response<List<Appointment>?>
    suspend fun createAppointment(data: CreateAppointmentRequest): Response<Appointment?>
    suspend fun login(data: LoginCheckRequest): Response<LoginCheckResponse?>

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(Android) {
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                        })
                    }
                    install(Logging) {
                        logger = Logger.DEFAULT
                        level = LogLevel.HEADERS
                        filter { request ->
                            request.url.host.contains("medifax.devscast.tech")
                        }
                        sanitizeHeader { header -> header == HttpHeaders.Authorization }
                    }
                }
            )
        }
    }
}