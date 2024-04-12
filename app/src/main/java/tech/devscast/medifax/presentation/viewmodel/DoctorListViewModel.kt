package tech.devscast.medifax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.devscast.medifax.domain.repository.DoctorRepository
import javax.inject.Inject


@HiltViewModel
class DoctorListViewModel @Inject constructor(
    private val repository: DoctorRepository
) : ViewModel() {
}
