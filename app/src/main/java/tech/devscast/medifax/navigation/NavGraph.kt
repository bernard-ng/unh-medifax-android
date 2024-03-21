package tech.devscast.medifax.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tech.devscast.medifax.ui.screens.AppointmentScreen
import tech.devscast.medifax.ui.screens.GetStartedScreen
import tech.devscast.medifax.ui.screens.HomeScreen
import tech.devscast.medifax.ui.screens.OnBoardingScreen
import tech.devscast.medifax.ui.screens.SignInScreen
import tech.devscast.medifax.ui.screens.SignUpScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Destination.OnBoarding.route) {
            OnBoardingScreen(
                onBoardingCompleted = {
                    navController.popBackStack()
                    navController.navigate(Destination.GetStarted.route)
                }
            )
        }
        composable(route = Destination.GetStarted.route) {
            GetStartedScreen()
        }
        composable(route = Destination.SignIn.route) {
            SignInScreen()
        }
        composable(route = Destination.SignUp.route) {
            SignUpScreen()
        }
        composable(route = Destination.Appointment.route) {
            AppointmentScreen()
        }
        composable(route = Destination.Home.route) {
            HomeScreen()
        }
    }
}