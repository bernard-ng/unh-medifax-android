package tech.devscast.medifax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import tech.devscast.medifax.ui.screens.EventHandlingScreen
import tech.devscast.medifax.ui.screens.OnboardingScreen
import tech.devscast.medifax.ui.screens.SignIn
import tech.devscast.medifax.ui.screens.SignUp
import tech.devscast.medifax.ui.screens.SplashScreen
import tech.devscast.medifax.ui.screens.TouchDrawingScreen
import tech.devscast.medifax.ui.theme.MedifaxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedifaxTheme {
                SignUp()
            }
        }
    }
}
