package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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

    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn {
            repeat(5) {
                item {
                    DoctorListItem(doctor = doctor)
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewShimmerAnimation() {
    MedifaxTheme {
        DoctorListScreen()
    }
}
