package tech.devscast.medifax.navigation

sealed class Destination(val route: String) {
    data object OnBoarding : Destination(route = "onboarding")
    data object GetStarted : Destination(route = "get_started")
    data object SignUp : Destination(route = "sign_up")
    data object SignIn : Destination(route = "sign_in")
    data object Home : Destination(route = "home")
    data object Appointment : Destination(route = "appointment")
    data object Profile : Destination(route = "profile")
}
