package tech.devscast.medifax.presentation.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.domain.PreferencesKeys
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {
    fun onboardingCompleted() {
        viewModelScope.launch(Dispatchers.IO) {
            preferences.edit {
                putBoolean(PreferencesKeys.IS_ONBOARDING_COMPLETED, true)
            }
            Log.i("MEDIFAX", "onboard completed saved")
        }
    }
}
