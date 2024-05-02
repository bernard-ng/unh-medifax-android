package tech.devscast.medifax.presentation.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.entity.Appointment
import tech.devscast.medifax.domain.PreferencesKeys
import tech.devscast.medifax.domain.repository.AppointmentRepository
import javax.inject.Inject

data class AppointmentViewState(
    val appointments: List<Appointment> = emptyList(),
    val createdAppointment: Appointment? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    private val repository: AppointmentRepository,
    private val preferences: SharedPreferences
) : ViewModel() {

    var uiState by mutableStateOf(AppointmentViewState())
        private set

    fun fetchAppointment() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            repository.findForPatient().collectLatest { response ->
                uiState = uiState.copy(
                    appointments = response.data ?: emptyList(),
                    isLoading = false,
                    errorMessage = if (!response.success) response.description else null
                )
            }
        }
    }
}
