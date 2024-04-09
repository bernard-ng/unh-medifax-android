package tech.devscast.medifax.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.navigation.BottomNavigationBar
import tech.devscast.medifax.ui.screens.home.components.HealthArticles
import tech.devscast.medifax.ui.screens.home.components.HeroSection
import tech.devscast.medifax.ui.screens.home.components.ServiceButtons
import tech.devscast.medifax.ui.theme.MedifaxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Medifax") })
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFD5ECF4))
        ) {
            HeroSection()
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(18.dp)
                    .fillMaxHeight()
            ) {
                ServiceButtons()
                HealthArticles()
            }
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
    MedifaxTheme(darkTheme = true) {
        Surface {
            HomeScreen()
        }
    }
}