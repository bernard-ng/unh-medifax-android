package tech.devscast.medifax.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tech.devscast.medifax.ui.screens.AppointmentScreen
import tech.devscast.medifax.ui.screens.GetStartedScreen
import tech.devscast.medifax.ui.screens.HomeScreen
import tech.devscast.medifax.ui.screens.onboarding.OnBoardingScreen
import tech.devscast.medifax.ui.screens.SignInScreen
import tech.devscast.medifax.ui.screens.SignUpScreen
import tech.devscast.medifax.viewmodel.OnboardingViewModel

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

