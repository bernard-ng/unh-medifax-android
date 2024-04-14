package tech.devscast.medifax.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.domain.repository.DoctorRepository
import javax.inject.Inject

data class DoctorListViewState(
    val doctors: List<Doctor> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class DoctorListViewModel @Inject constructor(
    private val repository: DoctorRepository
) : ViewModel() {

    var uiState by mutableStateOf(DoctorListViewState())
        private set

    fun fetchDoctors() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            repository.findAll().collectLatest { response ->
                uiState = uiState.copy(
                    doctors = response.data ?: emptyList(),
                    isLoading = false,
                    errorMessage = if (!response.success) response.description else null
                )
            }
        }
    }
}
