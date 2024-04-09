package tech.devscast.medifax.ui.screens.doctor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.model.Appointment
import tech.devscast.medifax.model.Doctor
import tech.devscast.medifax.model.Specialization
import tech.devscast.medifax.ui.screens.doctor.components.DoctorListItem
import tech.devscast.medifax.ui.theme.MedifaxTheme
import tech.devscast.medifax.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetailScreen(
    onBackClicked: () -> Unit = {},
    onBookAppointment: () -> Unit = {}
) {
    var message by remember { mutableStateOf("") }
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

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        DoctorListItem(doctor = doctor, {})
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "A propos",
            fontFamily = poppinsFontFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipi elit, sed do eiusmod tempor incididunt ut laore et dolore magna aliqua. Ut enim ad minim veniam... Read more",
            color = Color(0x66221F1F),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))
        AnimatedVisibility(visible = doctor.isAvailable) {
            OutlinedTextField(
                value = message,
                onValueChange = { if (it.length <= 255) message = it },
                label = { Text(text = "Description") },
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "${message.length} / 255",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                },
                shape = MaterialTheme.shapes.medium,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            enabled = doctor.isAvailable
        ) {
            Text(
                text = "Réservez un rendez-vous",
                fontSize = 16.sp,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewAppointmentScreen() {
    MedifaxTheme {
        DoctorDetailScreen(
            onBackClicked = {},
            onBookAppointment = {}
        )
    }
}