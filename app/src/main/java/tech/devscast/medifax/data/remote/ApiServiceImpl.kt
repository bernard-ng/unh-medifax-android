package tech.devscast.medifax.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.data.remote.dto.CreateAppointmentRequest
import tech.devscast.medifax.data.remote.dto.LoginCheckRequest
import tech.devscast.medifax.data.remote.dto.LoginCheckResponse
import tech.devscast.medifax.data.remote.dto.Response

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {
    override suspend fun getDoctor(id: String): Response<Doctor?> {
        return tryRequest {
            val url = Endpoints.DOCTOR.replace("{id}", id)
            val doctor: Doctor = client.get(url).body()
            Response(doctor, success = true)
        }
    }

    override suspend fun getDoctors(): Response<List<Doctor>?> {
        return tryRequest {
            val doctors: List<Doctor> = client.get(Endpoints.DOCTORS).body()
            Response(doctors, success = true)
        }
    }

    override suspend fun getPatient(id: String): Response<Patient?> {
       return tryRequest {
           val url = Endpoints.PATIENT.replace("{id}", id)
           val patient: Patient = client.get(url).body()
           Response(patient, success = true)
       }
    }

    override suspend fun getPatientAppointments(id: String): Response<List<Appointment>?> {
        return tryRequest {
            val url = Endpoints.PATIENT_APPOINTMENTS.replace("{id}", id)
            val appointment: List<Appointment> = client.get(url).body()
            Response(appointment, success = true)
        }
    }

    override suspend fun createAppointment(data: CreateAppointmentRequest): Response<Appointment?> {
        return tryRequest {
            val response: Appointment = client.post(Endpoints.APPOINTMENT) {
                contentType(ContentType.Application.Json)
                setBody(data)
            }.body()
            Response(response, success = true)
        }
    }

    override suspend fun login(data: LoginCheckRequest): Response<LoginCheckResponse?> {
        return tryRequest {
            val response: LoginCheckResponse = client.post(Endpoints.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(data)
            }.body()
            Response(response, success = true)
        }
    }

    private suspend fun <T> tryRequest(request: suspend () -> Response<T?>): Response<T?> {
        return try {
            request()
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
            null,
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