package tech.devscast.medifax

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.devscast.medifax.presentation.navigation.Destination
import tech.devscast.medifax.presentation.navigation.DefaultNavGraph
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
                val isCompleted = preferences.getBoolean("completed", false)
                val startDestination =
                    if (isCompleted) Destination.GetStarted.route else Destination.OnBoarding.route
                val navController = rememberNavController()

                Surface(color = MaterialTheme.colorScheme.background) {
                    DefaultNavGraph(
                        navController = navController,
                        startDestination = startDestination,
                    )
                }
            }
        }
    }
}
