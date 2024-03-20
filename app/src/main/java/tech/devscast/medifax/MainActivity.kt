package tech.devscast.medifax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import tech.devscast.medifax.ui.screens.EventHandlingScreen
import tech.devscast.medifax.ui.screens.OnboardingScreen
import tech.devscast.medifax.ui.screens.TouchDrawingScreen
import tech.devscast.medifax.ui.theme.MedifaxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MedifaxTheme {
                EventHandlingScreen()
            }
        }
    }
}
