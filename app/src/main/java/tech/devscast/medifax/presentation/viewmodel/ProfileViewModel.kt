package tech.devscast.medifax.presentation.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.entity.Patient
import tech.devscast.medifax.domain.PreferencesKeys
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

data class ProfileViewState(
    val patient: Patient? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = true
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PatientRepository,
    private val preferences: SharedPreferences
) : ViewModel() {

    var uiState by mutableStateOf(ProfileViewState())
        private set

    fun fetchCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            uiState = try {
                val userId = preferences.getString(PreferencesKeys.CURRENT_USER_ID, null)
                    ?: throw Exception("Désolé vous n'êtes plus connecté")

                val response = repository.find(userId)
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

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            preferences.edit {
                putBoolean(PreferencesKeys.IS_LOGGED_IN, false)
                putString(PreferencesKeys.CURRENT_USER_ID, null)
                putString(PreferencesKeys.JWT_TOKEN, null)
            }
        }
    }
}
