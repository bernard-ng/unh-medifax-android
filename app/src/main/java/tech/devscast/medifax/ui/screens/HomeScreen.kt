package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import tech.devscast.medifax.ui.theme.MedifaxTheme

@Composable
fun HomeScreen() {
    Surface {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "home screen")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_8_pro")
@Composable
fun PreviewHomeScreen() {
    MedifaxTheme {
        HomeScreen()
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_8_pro")
@Composable
fun PreviewHomeScreenDark() {
    MedifaxTheme (darkTheme = true) {
        Surface {
            HomeScreen()
        }
    }
}