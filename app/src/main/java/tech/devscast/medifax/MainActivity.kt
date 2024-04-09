package tech.devscast.medifax

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.devscast.medifax.navigation.Destination
import tech.devscast.medifax.navigation.DefaultNavGraph
import tech.devscast.medifax.ui.theme.MedifaxTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            MedifaxTheme (darkTheme = false) {
                val isCompleted = preferences.getBoolean("completed", false)
                val startDestination = if (isCompleted) Destination.GetStarted.route else Destination.OnBoarding.route
                val navController = rememberNavController()

                Surface {
                    DefaultNavGraph(
                        navController = navController,
                        startDestination = startDestination,
                    )
                }
            }
        }
    }
}
