package tech.devscast.medifax.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.ui.screens.GetStartedScreen
import tech.devscast.medifax.ui.screens.doctor.DoctorDetailScreen
import tech.devscast.medifax.ui.screens.onboarding.OnBoardingScreen
import tech.devscast.medifax.ui.screens.onboarding.OnboardingViewModel

@Composable
fun AuthenticatedNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    ) {
        composable(route = Destination.OnBoarding.route) {
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnBoardingScreen(
                viewModel = viewModel,
                onBoardingCompleted = {
                    navController.popBackStack()
                    navController.navigate(Destination.GetStarted.route)
                }
            )
        }

        composable(route = Destination.GetStarted.route) {
            GetStartedScreen(
                onSignInClick = { navController.navigate(Destination.SignIn.route) },
                onSignUpClick = { navController.navigate(Destination.SignUp.route) }
            )
        }

        composable(route = Destination.Appointment.route) {
            DoctorDetailScreen(
                onBackClicked = { navController.navigate(Destination.DoctorList.route) },
                {}
            )
        }
    }
}
