package tech.devscast.medifax.ui.screens.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.navigation.BottomNavigationBar
import tech.devscast.medifax.ui.theme.MedifaxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(navController: NavController = rememberNavController())
{
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Mon Agenda") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { contentPadding ->
        LazyColumn (
            modifier = Modifier
                .padding(contentPadding)
                .padding(32.dp)
        ) {
           item {
               Text("Appointements")
           }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun PreviewAppointmentScreen() {
    MedifaxTheme {
        AppointmentScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun PreviewAppointmentScreenDark() {
    MedifaxTheme(darkTheme = true) {
        Surface {
            AppointmentScreen()
        }
    }
}
