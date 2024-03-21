package tech.devscast.medifax.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.DataStoreRepository
import tech.devscast.medifax.navigation.Destination
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
//    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Destination.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
//            repository.readOnBoardingState().collect { completed ->
//                if (completed) {
//                    _startDestination.value = Destination.Home.route
//                } else {
//                    _startDestination.value = Destination.OnBoarding.route
//                }
//            }
            _isLoading.value = false
        }
    }

}