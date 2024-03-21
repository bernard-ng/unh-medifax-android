package tech.devscast.medifax.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.DataStoreRepository
import javax.inject.Inject

//@HiltViewModel
//class OnboardingViewModel @Inject constructor(
////    private val repository: DataStoreRepository
//) : ViewModel() {
//
//    fun saveOnBoardingState(completed: Boolean) {
//        viewModelScope.launch(Dispatchers.IO) {
//            //repository.saveOnBoardingState(completed = completed)
//        }
//    }
//}