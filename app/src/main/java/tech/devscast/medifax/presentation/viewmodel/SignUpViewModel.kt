package tech.devscast.medifax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: PatientRepository
): ViewModel() {

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(email, password, fullName)
        }
    }
}
