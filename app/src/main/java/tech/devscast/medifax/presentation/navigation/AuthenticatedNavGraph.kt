package tech.devscast.medifax.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.presentation.screens.doctor.DoctorDetailScreen
import tech.devscast.medifax.presentation.screens.doctor.DoctorListScreen
import tech.devscast.medifax.presentation.screens.home.HomeScreen
import tech.devscast.medifax.presentation.screens.profile.AppointmentScreen
import tech.devscast.medifax.presentation.screens.profile.ProfileScreen

@Composable
fun AuthenticatedNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    ) {

        composable(route = Destination.Home.route) {
            HomeScreen(navController)
        }

        composable(route = Destination.Profile.route) {
            ProfileScreen(navController)
        }

        composable(route = Destination.Appointment.route) {
            AppointmentScreen(navController)
        }

        composable(route = Destination.DoctorList.route) {
            DoctorListScreen(navController)
        }

        composable(route = Destination.DoctorDetail.route) {
            DoctorDetailScreen(navController)
        }
    }
}
