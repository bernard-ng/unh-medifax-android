package tech.devscast.medifax.presentation.screens.doctor

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.presentation.components.EmptyState
import tech.devscast.medifax.presentation.navigation.BottomNavigationBar
import tech.devscast.medifax.presentation.navigation.Destination
import tech.devscast.medifax.presentation.navigation.withArgument
import tech.devscast.medifax.presentation.screens.doctor.components.DoctorListItem
import tech.devscast.medifax.presentation.theme.MedifaxTheme
import tech.devscast.medifax.presentation.viewmodel.DoctorListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorListScreen(
    navController: NavController = rememberNavController(),
    viewModel: DoctorListViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchDoctors()
    }

    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nos Docteurs") },
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
                CircularProgressIndicator()
            }

            uiState.errorMessage != null -> {
                EmptyState(message = "Un problème est survenue")
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {
                if (uiState.doctors.isEmpty()) {
                    EmptyState("Aucun docteurs disponbile sur la plateforme à l'instant")
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(contentPadding)
                            .padding(32.dp)
                    ) {
                        items(uiState.doctors) { doctor: Doctor ->
                            DoctorListItem(doctor, onClick = {
                                navController.navigate(Destination.DoctorDetail.withArgument(doctor.id.toString()))
                            })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewDoctorListScreen() {
    MedifaxTheme {
        DoctorListScreen()
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewDoctorListScreenDark() {
    MedifaxTheme(darkTheme = true) {
        DoctorListScreen()
    }
}