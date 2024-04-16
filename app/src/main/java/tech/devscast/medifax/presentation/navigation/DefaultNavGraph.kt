package tech.devscast.medifax.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.presentation.screens.GetStartedScreen
import tech.devscast.medifax.presentation.screens.sign.SignInScreen
import tech.devscast.medifax.presentation.screens.sign.SignUpScreen
import tech.devscast.medifax.presentation.screens.onboarding.OnBoardingScreen

@Composable
fun DefaultNavGraph(
    navController: NavHostController = rememberNavController(),
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
            GetStartedScreen(
                onSignInClick = { navController.navigate(Destination.SignIn.route) },
                onSignUpClick = { navController.navigate(Destination.SignUp.route) }
            )
        }

        composable(route = Destination.SignIn.route) {
            SignInScreen(
                onSignUpClicked = { navController.navigate(Destination.SignIn.route) },
                onSignInCompleted = { navController.navigate(Destination.AuthenticatedNavHost.route) }
            )
        }

        composable(route = Destination.SignUp.route) {
            SignUpScreen(
                onSignInClicked = { navController.navigate(Destination.SignIn.route) },
                onSignUpCompleted = { navController.navigate(Destination.SignIn.route) }
            )
        }

        composable(route = Destination.AuthenticatedNavHost.route) {
            AuthenticatedNavGraph()
        }
    }
}
