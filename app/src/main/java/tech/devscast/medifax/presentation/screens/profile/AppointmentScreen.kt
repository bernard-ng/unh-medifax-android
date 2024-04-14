package tech.devscast.medifax.presentation.screens.profile

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.presentation.components.EmptyState
import tech.devscast.medifax.presentation.components.ProgressLoader
import tech.devscast.medifax.presentation.navigation.BottomNavigationBar
import tech.devscast.medifax.presentation.screens.profile.components.AppointmentItem
import tech.devscast.medifax.presentation.theme.MedifaxTheme
import tech.devscast.medifax.presentation.viewmodel.AppointmentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
    navController: NavController = rememberNavController(),
    viewModel: AppointmentViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = viewModel) {
        /**
         * Trigger the request when [AppointmentScreen] become visible
         * That's why [AppointmentViewModel.fetchAppointment] is called inside [LaunchedEffect]
         */
        viewModel.fetchAppointment()
    }

    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mon Agenda") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { contentPadding ->
        when {
            uiState.isLoading -> {
                ProgressLoader()
            }

            uiState.errorMessage != null -> {
                EmptyState(message = "Un problème est survenue")
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                if (uiState.appointments.isEmpty()) {
                    EmptyState(message = "Vous n'avez pris aucun rendez-vous pour l'instant")
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(contentPadding)
                            .padding(32.dp)
                    ) {
                        items(uiState.appointments) { appointment ->
                            AppointmentItem(appointment)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun PreviewAppointmentScreen() {
    MedifaxTheme {
        AppointmentScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun PreviewAppointmentScreenDark() {
    MedifaxTheme(darkTheme = true) {
        Surface {
            AppointmentScreen()
        }
    }
}
