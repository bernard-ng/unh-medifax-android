package tech.devscast.medifax.presentation.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.domain.PreferencesKeys
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

data class HomeViewState(
    val patient: Patient? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = true
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: PatientRepository,
    val preferences: SharedPreferences
): ViewModel() {
    var uiState by mutableStateOf(HomeViewState())
        private set

    fun fetchCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            uiState = try {
                val response = repository.me()
                uiState.copy(
                    patient = response.data,
                    isLoading = false,
                    errorMessage = if (!response.success) response.description else null
                )
            } catch (e: Exception) {
                uiState.copy(
                    isLoading = false,
                    isLoggedIn = false,
                    errorMessage = "Vous n'êtes plus connecté"
                )
            }
        }
    }
}