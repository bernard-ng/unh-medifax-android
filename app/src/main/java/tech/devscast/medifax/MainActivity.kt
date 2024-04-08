package tech.devscast.medifax

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.devscast.medifax.navigation.Destination
import tech.devscast.medifax.navigation.SetupNavGraph
import tech.devscast.medifax.ui.components.BottomNavigationBar
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
            MedifaxTheme (darkTheme = false) {
                val isCompleted = preferences.getBoolean("completed", false)
                val startDestination = if (isCompleted) Destination.GetStarted.route else Destination.OnBoarding.route
                val navController = rememberNavController()

                Surface {
                    SetupNavGraph(
                        navController = navController,
                        startDestination = startDestination,
                    )
                }
            }
        }
    }
}
