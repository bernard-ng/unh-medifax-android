package tech.devscast.medifax.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(val route: String, val label: String = "", val icon: ImageVector? = null) {
    // navigation hosts
    data object AuthenticatedNavHost : Destination(route = "_authenticated_navhost")
    data object DefaultNavHost : Destination(route = "_default_navhost")

    // unauthenticated screens
    data object OnBoarding : Destination(route = "onboarding")
    data object GetStarted : Destination(route = "get_started")
    data object SignUp : Destination(route = "sign_up")
    data object SignIn : Destination(route = "sign_in")
    data object Logout: Destination(route = "logout")


    // authenticated screens
    data object Home : Destination(route = "home", label = "Accueil", icon = Icons.Rounded.Home)
    data object Appointment : Destination(route = "appointment", label = "Agenda", icon = Icons.Rounded.DateRange)
    data object Profile : Destination(route = "profile", label = "Profil", icon = Icons.Rounded.Person)

    data object DoctorList : Destination(route = "doctors")
    data object DoctorDetail : Destination(route = "doctor_details")
}

fun Destination.withArgument(arg: String): String {
    return "${route}/$arg"
}

fun getBottomNavigationDestinations(): List<Destination> {
    return listOf(Destination.Home, Destination.Appointment, Destination.Profile)
}

fun getStartDestination(isOnboardingCompleted: Boolean, isLoggedIn: Boolean): Destination {

    val destination: Destination = if (isOnboardingCompleted) {
        if (isLoggedIn) {
            Destination.AuthenticatedNavHost
        } else {
            Destination.GetStarted
        }
    } else {
        Destination.OnBoarding
    }

    return destination
}