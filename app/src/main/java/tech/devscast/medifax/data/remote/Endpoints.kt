package tech.devscast.medifax.data.remote

object Endpoints {
    private const val BASE_URL = "https://medifax.devscast.tech/api"

    const val DOCTORS = "$BASE_URL/doctors"
    const val DOCTOR = "$BASE_URL/doctors/{id}"
    const val LOGIN = "$BASE_URL/login_check"
    const val PATIENT = "$BASE_URL/patients/{id}"
    const val APPOINTMENT = "$BASE_URL/appointments"
    const val PATIENT_APPOINTMENTS = "$BASE_URL/appointments/patient"
    const val REGISTER = "$BASE_URL/register"
    const val ME = "$BASE_URL/me"
}
