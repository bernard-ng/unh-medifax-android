package tech.devscast.medifax

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.devscast.medifax.domain.PreferencesKeys
import tech.devscast.medifax.presentation.navigation.DefaultNavGraph
import tech.devscast.medifax.presentation.navigation.getStartDestination
import tech.devscast.medifax.presentation.theme.MedifaxTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            MedifaxTheme(darkTheme = false) {
                val isOnboardingCompleted = preferences.getBoolean(PreferencesKeys.IS_ONBOARDING_COMPLETED, false)
                val isLoggedIn = preferences.getBoolean(PreferencesKeys.IS_LOGGED_IN, false)

                Surface(color = MaterialTheme.colorScheme.background) {
                    DefaultNavGraph(
                        navController = rememberNavController(),
                        startDestination = getStartDestination(
                            isOnboardingCompleted,
                            isLoggedIn
                        ).route,
                    )
                }
            }
        }
    }
}
