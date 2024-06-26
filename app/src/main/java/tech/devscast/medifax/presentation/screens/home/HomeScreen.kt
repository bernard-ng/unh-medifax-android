package tech.devscast.medifax.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.presentation.components.ProgressLoader
import tech.devscast.medifax.presentation.navigation.BottomNavigationBar
import tech.devscast.medifax.presentation.navigation.Destination
import tech.devscast.medifax.presentation.screens.home.components.HealthArticles
import tech.devscast.medifax.presentation.screens.home.components.HeroSection
import tech.devscast.medifax.presentation.screens.home.components.ServiceButton
import tech.devscast.medifax.presentation.theme.MedifaxTheme
import tech.devscast.medifax.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchCurrentUser()
    }

    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Medifax") })
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFD5ECF4))
        ) {
            when {
                uiState.errorMessage != null -> {
                    Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_LONG)
                        .show()
                }

                !uiState.isLoggedIn -> {
                    navController.navigate(Destination.SignIn.route)
                }

                uiState.isLoading -> {
                    ProgressLoader()
                }

                uiState.patient != null -> {
                    HeroSection(uiState.patient)
                }
            }

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(18.dp)
                    .fillMaxHeight()
            ) {
                val context = LocalContext.current
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ServiceButton(
                        text = "Top Doctors",
                        icon = Icons.Default.Person,
                        onClick = { navController.navigate(Destination.DoctorList.route) }
                    )
                    ServiceButton(
                        text = "Pharmacy",
                        icon = Icons.Default.MedicalServices,
                        onClick = { Toast.makeText(context, "Indisponible", Toast.LENGTH_LONG).show() }
                    )
                    ServiceButton(
                        text = "Ambulance",
                        icon = Icons.Default.CarCrash,
                        onClick = { Toast.makeText(context, "Indisponible", Toast.LENGTH_LONG).show() }
                    )
                }
                HealthArticles()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_8_pro")
@Composable
fun PreviewHomeScreen() {
    MedifaxTheme {
        HomeScreen()
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_8_pro")
@Composable
fun PreviewHomeScreenDark() {
    MedifaxTheme(darkTheme = true) {
        Surface {
            HomeScreen()
        }
    }
}