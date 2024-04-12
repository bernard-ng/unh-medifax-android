package tech.devscast.medifax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PatientRepository
) : ViewModel() {
}
