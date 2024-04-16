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
import tech.devscast.medifax.domain.PreferencesKeys
import tech.devscast.medifax.domain.dto.LoginCheckResponse
import tech.devscast.medifax.domain.dto.Response
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

data class SignInViewState(
    val token: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: PatientRepository,
    private val preferences: SharedPreferences
) : ViewModel() {

    var uiState by mutableStateOf(SignInViewState())
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            val response = getJwtTokenOnSuccess(email, password)

            uiState = uiState.copy(
                token = response.data?.token,
                isLoading = false,
                errorMessage = if (!response.success) response.description else null
            )
        }
    }

    private suspend fun getJwtTokenOnSuccess(email: String, password: String): Response<LoginCheckResponse?> {
        val response = repository.login(email, password)
        if (response.success && response.data != null) {
            val token = response.data.token

            preferences.edit {
                putString(PreferencesKeys.JWT_TOKEN, token)
                putBoolean(PreferencesKeys.IS_LOGGED_IN, true)
            }
        }

        return response
    }
}
