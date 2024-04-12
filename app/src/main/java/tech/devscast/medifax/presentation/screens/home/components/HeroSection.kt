package tech.devscast.medifax.presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R

@Composable
fun HeroSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp, start = 24.dp, end = 24.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
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
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Bienvenue !",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF221F1F)
            )
            Text(
                "Henriette",
                fontSize = 24.sp,
                color = Color(0xFF221F1F)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Comment allez-vous ?",
                fontWeight = FontWeight.Bold,
                color = Color(0x80221F1F),
                fontSize = 18.sp
            )
        }
        Row (verticalAlignment = Alignment.Bottom) {
            Image(
                painter = painterResource(id = R.drawable.welcome_doctor),
                contentDescription = "welcome illustration",
                modifier = Modifier.size(300.dp)
            )
        }
    }
}
