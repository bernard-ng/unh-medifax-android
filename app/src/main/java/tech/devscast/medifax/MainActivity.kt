package tech.devscast.medifax

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.devscast.medifax.navigation.Destination
import tech.devscast.medifax.navigation.SetupNavGraph
import tech.devscast.medifax.ui.theme.MedifaxTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            MedifaxTheme {
                val isCompleted = preferences.getBoolean("completed", false)
                val startDestination = if (isCompleted) Destination.Home.route else Destination.OnBoarding.route
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    startDestination = startDestination,
                )
            }
        }
    }
}
