package tech.devscast.medifax.ui.screens.doctor.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.data.entity.Doctor
import tech.devscast.medifax.data.entity.Specialization
import tech.devscast.medifax.ui.theme.MedifaxTheme
import tech.devscast.medifax.ui.theme.poppinsFontFamily

@Composable
fun DoctorListItem(doctor: Doctor, onClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick() })
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .shadow(4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profil),
                    contentDescription = "Photo du Médecin",
                    modifier = Modifier
                        .size(100.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = doctor.fullName,
                    fontFamily = poppinsFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = doctor.specialization.name,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )
                FilterChip(
                    selected = doctor.isAvailable,
                    onClick = { Log.d("Assist chip", "hello world") },
                    label = { Text("Disponible") },
                    leadingIcon = if (doctor.isAvailable) {
                        {
                            Icon(
                                Icons.Filled.Done,
                                contentDescription = "Localized description",
                                Modifier.size(AssistChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                    shape = MaterialTheme.shapes.small
                )
            }
        }
    }
}

@Preview()
@Composable
fun PreviewDoctorListItem() {
    MedifaxTheme {
        val doctor = Doctor(
            12,
            "hello@gmail.com",
            "Dr. Vaamana",
            "+23333",
            true,
            Specialization(12, "Dentists", ""),
            "",
        )
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            DoctorListItem(doctor = doctor, {})
        }
    }
}

@Preview()
@Composable
fun PreviewDoctorListItemDark() {
    MedifaxTheme(darkTheme = true) {
        val doctor = Doctor(
            12,
            "hello@gmail.com",
            "Dr. Vaamana",
            "+23333",
            true,
            Specialization(12, "Dentists", ""),
            "",
        )
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            DoctorListItem(doctor = doctor, {})
        }
    }
}