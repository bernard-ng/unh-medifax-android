package tech.devscast.medifax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.devscast.medifax.data.repository.DoctorRepositoryImpl
import tech.devscast.medifax.domain.repository.DoctorRepository
import javax.inject.Inject

@HiltViewModel
class DoctorDetailViewModel @Inject constructor(
    private val repository: DoctorRepository
): ViewModel() {
}
