package tech.devscast.medifax.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

sealed class RegistrationStatus{
    object Success : RegistrationStatus()
    object Error : RegistrationStatus()
}
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: PatientRepository
): ViewModel() {
    private val _registrationsStatus = MutableLiveData<RegistrationStatus>()
    val registrationStatus: LiveData<RegistrationStatus> get() = _registrationsStatus

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.register(email, password, fullName)
            if (response.success){
                _registrationsStatus.postValue(RegistrationStatus.Success)
            } else {
                _registrationsStatus.postValue(RegistrationStatus.Error)
            }
        }
    }
}
