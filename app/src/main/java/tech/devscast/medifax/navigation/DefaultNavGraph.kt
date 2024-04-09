package tech.devscast.medifax.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tech.devscast.medifax.ui.screens.GetStartedScreen
import tech.devscast.medifax.ui.screens.sign.SignInScreen
import tech.devscast.medifax.ui.screens.sign.SignUpScreen
import tech.devscast.medifax.ui.screens.onboarding.OnBoardingScreen

@Composable
fun DefaultNavGraph(
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
            GetStartedScreen(
                onSignInClick = { navController.navigate(Destination.SignIn.route) },
                onSignUpClick = { navController.navigate(Destination.SignUp.route) }
            )
        }

        composable(route = Destination.SignIn.route) {
            SignInScreen(
                onSignUpClicked = { navController.navigate(Destination.SignIn.route) },
                onSignInCompleted = { navController.navigate(Destination.Home.route) }
            )
        }

        composable(route = Destination.SignUp.route) {
            SignUpScreen(
                onSignInClicked = { navController.navigate(Destination.SignIn.route) },
                onSignUpCompleted = { navController.navigate(Destination.Home.route) }
            )
        }

        composable(route = Destination.AuthenticatedNavHost.route) {
            AuthenticatedNavGraph()
        }
    }
}
