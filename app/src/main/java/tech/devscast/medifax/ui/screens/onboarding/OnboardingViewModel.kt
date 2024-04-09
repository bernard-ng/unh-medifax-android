package tech.devscast.medifax.ui.screens.onboarding

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: SharedPreferences
) : ViewModel() {
    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.edit {
                putBoolean("completed", completed)
            }
        }
    }
}
