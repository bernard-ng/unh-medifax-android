package tech.devscast.medifax.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.theme.MedifaxTheme

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        ProfileHeader()
        Spacer(modifier = Modifier.height(8.dp))
        StatsSection()
        Spacer(modifier = Modifier.height(24.dp))
        SettingsSection()
    }
}

@Composable
fun ProfileHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.doctor_female),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)

        )
        Text("Chita", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}


@Composable
fun StatsSection() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .width(500.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem("Heart rate", "215bpm")
            HorizontalDivider(
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp), color = Color.LightGray
            )
            StatItem("Calories", "756cal")
            HorizontalDivider(
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp), color = Color.LightGray
            )
            StatItem("Weight", "103lbs")
        }
    }
}

@Composable
fun StatItem(title: String, value: String, titleColor: Color = Color.Blue, valueColor: Color= Color.Blue) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Medium, fontSize = 18.sp, color = valueColor)
        Text(title, fontSize = 14.sp, color = titleColor)
    }
}

@Composable
fun SettingsSection() {
    Column {
        ProfileOption("Appointment", Icons.Default.DateRange)
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        ProfileOption("Payment Method", Icons.Default.CreditCard)
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        ProfileOption("Logout", Icons.AutoMirrored.Filled.ExitToApp)
    }
    Spacer(modifier = Modifier.height(230.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {}) {
            Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.Blue)
        }
        IconButton(onClick = {}) {
            Icon(Icons.Filled.Person, contentDescription = "Profil", tint = Color.Blue)
        }
    }

}
@Composable
fun ProfileOption(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, tint = Color.Blue)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, fontSize = 20.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun DefaultPreview() {
    MedifaxTheme {
        ProfileScreen()
    }
}