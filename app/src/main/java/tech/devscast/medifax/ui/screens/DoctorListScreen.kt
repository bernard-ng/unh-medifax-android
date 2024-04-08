package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.devscast.medifax.model.Appointment
import tech.devscast.medifax.model.Doctor
import tech.devscast.medifax.model.Specialization
import tech.devscast.medifax.ui.components.DoctorListItem
import tech.devscast.medifax.ui.theme.MedifaxTheme

@Composable
fun DoctorListScreen() {
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

    Surface {
        LazyColumn (
            modifier = Modifier
                .padding(20.dp)
        ) {
            repeat(5) {
                item {
                    DoctorListItem(doctor = doctor)
                    Spacer(modifier = Modifier.height(8.dp))
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