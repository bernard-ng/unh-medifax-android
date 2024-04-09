package tech.devscast.medifax.ui.screens.doctor

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.model.Appointment
import tech.devscast.medifax.model.Doctor
import tech.devscast.medifax.model.Specialization
import tech.devscast.medifax.navigation.BottomNavigationBar
import tech.devscast.medifax.navigation.Destination
import tech.devscast.medifax.navigation.withArgument
import tech.devscast.medifax.ui.screens.doctor.components.DoctorListItem
import tech.devscast.medifax.ui.theme.MedifaxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorListScreen(navController: NavController = rememberNavController()) {
    val doctor = Doctor(
        12,
        "hello@gmail.com",
        listOf("a", "b"),
        "aaaa",
        "Dr. Vaamana",
        "+23333",
        "",
        "",
        false,
        true,
        emptyList<Appointment>(),
        Specialization(12, "Dentists", "", emptyList())
    )

   Scaffold (
       topBar = {
           TopAppBar(
               title = { Text("Nos Docteurs") },
               navigationIcon = {
                   IconButton(onClick = { /* do something */ }) {
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
           repeat(20) {
               item {
                   DoctorListItem(
                       doctor = doctor,
                       onClick = {
                           navController.navigate(Destination.DoctorDetail.withArgument(doctor.id.toString()))
                       }
                   )
                   Spacer(modifier = Modifier.height(16.dp))
               }
           }
       }
   }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewDoctorListScreen() {
    MedifaxTheme {
        DoctorListScreen()
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewDoctorListScreenDark() {
    MedifaxTheme (darkTheme = true) {
        DoctorListScreen()
    }
}