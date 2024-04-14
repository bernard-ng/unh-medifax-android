package tech.devscast.medifax.data.remote

import android.content.SharedPreferences
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.domain.ApiService
import tech.devscast.medifax.domain.PreferencesKeys
import tech.devscast.medifax.domain.dto.CreateAppointmentRequest
import tech.devscast.medifax.domain.dto.LoginCheckRequest
import tech.devscast.medifax.domain.dto.LoginCheckResponse
import tech.devscast.medifax.domain.dto.RegisterRequest
import tech.devscast.medifax.domain.dto.Response
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val client: HttpClient,
    private val preferences: SharedPreferences
) : ApiService {

    private val token = preferences.getString(PreferencesKeys.JWT_TOKEN, null)

    override suspend fun getDoctor(id: String): Response<Doctor?> {
        return tryRequest {
            val url = Endpoints.DOCTOR.replace("{id}", id)
            val doctor: Doctor = client.get(url) {
                bearerAuth(token.toString())
                contentType(ContentType.Application.Json)
            }.body()
            Response(doctor, success = true)
        }
    }

    override suspend fun getDoctors(): Response<List<Doctor>?> {
        return tryRequest {
            val doctors: List<Doctor> = client.get(Endpoints.DOCTORS) {
                bearerAuth(token.toString())
                contentType(ContentType.Application.Json)
            }.body()
            Response(doctors, success = true)
        }
    }

    override suspend fun getPatient(id: String): Response<Patient?> {
        return tryRequest {
            val url = Endpoints.PATIENT.replace("{id}", id)
            val patient: Patient = client.get(url) {
                bearerAuth(token.toString())
                contentType(ContentType.Application.Json)
            }.body()
            Response(patient, success = true)
        }
    }

    override suspend fun getPatientAppointments(id: String): Response<List<Appointment>?> {
        return tryRequest {
            val url = Endpoints.PATIENT_APPOINTMENTS.replace("{id}", id)
            val appointment: List<Appointment> = client.get(url) {
                bearerAuth(token.toString())
                contentType(ContentType.Application.Json)
            }.body()
            Response(appointment, success = true)
        }
    }

    override suspend fun createAppointment(data: CreateAppointmentRequest): Response<Appointment?> {
        return tryRequest {
            val response: Appointment = client.post(Endpoints.APPOINTMENT) {
                bearerAuth(token.toString())
                contentType(ContentType.Application.Json)
                setBody(data)
            }.body()
            Response(response, success = true)
        }
    }

    override suspend fun login(data: LoginCheckRequest): Response<LoginCheckResponse?> {
        return tryRequest (requireToken = false) {
            val response: LoginCheckResponse = client.post(Endpoints.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(data)
            }.body()
            Response(response, success = true)
        }
    }

    override suspend fun register(data: RegisterRequest): Response<Patient?> {
        return tryRequest (requireToken = false) {
            val response: Patient = client.post(Endpoints.REGISTER) {
                contentType(ContentType.Application.Json)
                setBody(data)
            }.body()
            Response(response, success = true)
        }
    }

    override suspend fun me(): Response<Patient?> {
        return tryRequest {
            val response: Patient = client.get(Endpoints.ME) {
                bearerAuth(token.toString())
                contentType(ContentType.Application.Json)
            }.body()
            Response(response, success = true)
        }
    }

    private suspend fun <T> tryRequest(requireToken: Boolean = true, request: suspend () -> Response<T?>): Response<T?> {
        return try {
            if (token != null || !requireToken) {
                request()
            } else {
                Response(data = null, success = false, code = 401, description = "Déconnecté")
            }
        } catch (e: ClientRequestException) {
            handleHttpException<T>(e.response)
        } catch (e: ServerResponseException) {
            handleHttpException<T>(e.response)
        } catch (e: Exception) {
            handleException<T>()
        }
    }

    private fun <T> handleHttpException(response: HttpResponse): Response<T?> {
        return Response(
            data = null,
            code = response.status.value,
            description = response.status.description,
            success = false
        )
    }

    private fun <T> handleException(): Response<T?> {
        return Response(
            data = null,
            success = false,
            code = 0,
            description = "Désolé une erreur est survenue, vérifiez votre connexion internet"
        )
    }
}