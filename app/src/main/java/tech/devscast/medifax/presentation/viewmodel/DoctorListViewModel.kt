package tech.devscast.medifax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.domain.repository.DoctorRepository
import javax.inject.Inject


@HiltViewModel
class DoctorListViewModel @Inject constructor(
    private val repository: DoctorRepository
) : ViewModel() {
}
