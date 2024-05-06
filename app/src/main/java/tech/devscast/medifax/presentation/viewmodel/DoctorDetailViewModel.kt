package tech.devscast.medifax.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.data.repository.DoctorRepositoryImpl
import tech.devscast.medifax.domain.repository.AppointmentRepository
import tech.devscast.medifax.domain.repository.DoctorRepository
import javax.inject.Inject

data class DoctorDetailViewState(
    val doctor: Doctor? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val createdAppointment: Appointment? = null
)

@HiltViewModel
class DoctorDetailViewModel @Inject constructor(
    private val repository: DoctorRepository,
    private val appointmentRepository: AppointmentRepository
): ViewModel() {

    var uiState by mutableStateOf(DoctorDetailViewState())
        private set

    fun createAppointment(doctorId: String, description: String, date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            val response = appointmentRepository.createAppointment(doctorId, description, date)
            uiState = uiState.copy(
                isLoading = false,
                createdAppointment = response.data,
                errorMessage = if (!response.success) response.description else null
            )
        }
    }

    fun fetchDoctor(doctorId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            val response = repository.find(doctorId)
            uiState = uiState.copy(
                doctor = response.data,
                isLoading = false,
                errorMessage = if (!response.success) response.description else null
            )
        }
    }
}
