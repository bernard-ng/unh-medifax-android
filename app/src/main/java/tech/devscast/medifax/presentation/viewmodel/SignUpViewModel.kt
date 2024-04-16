package tech.devscast.medifax.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

data class SignUpViewState(
    val patient: Patient? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: PatientRepository
): ViewModel() {

    var uiState by mutableStateOf(SignUpViewState())
        private set

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            val response = repository.register(email, password, fullName)

            uiState = uiState.copy(
                patient = response.data,
                isLoading = false,
                errorMessage = if (!response.success) response.description else null
            )
        }
    }
}
