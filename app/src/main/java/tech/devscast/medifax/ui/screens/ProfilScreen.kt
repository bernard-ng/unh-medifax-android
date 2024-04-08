package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.theme.MedifaxTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(100.dp)
                    .border(BorderStroke(5.dp, MaterialTheme.colorScheme.primary), CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.doctor_svgrepo_com),
                    contentDescription = "Profile picture",
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("The Don Dan", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem("Heart rate", "215bpm", Icons.Default.HeartBroken)
            VerticalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.height(50.dp)
            )
            StatItem("Calories", "756cal", Icons.Default.LocalFireDepartment)
            VerticalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.height(50.dp)
            )
            StatItem("Weight", "103lbs", Icons.Default.SportsGymnastics)
        }

        Spacer(modifier = Modifier.height(64.dp))
        Column {
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            ListItem("Appointment", Icons.Default.CalendarMonth)
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            ListItem("Payment Method", Icons.Default.Payment)
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            ListItem("Logout", Icons.AutoMirrored.Filled.Logout)
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        }
    }
}

@Composable
fun StatItem(label: String, value: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(42.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ListItem(text: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(onClick = { /*TODO*/ }) {
            Icon(icon, contentDescription = null)
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(text, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, contentDescription = null)
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun PreviewUserProfileScreen() {
    MedifaxTheme {
        UserProfileScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun PreviewProfileScreenDark() {
    MedifaxTheme(darkTheme = true) {
        Surface {
            UserProfileScreen()
        }
    }
}