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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.components.BottomNavigationBar
import tech.devscast.medifax.ui.theme.MedifaxTheme

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFD5ECF4))
        ) {
            GreetingSection()

            Column (
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

@Composable
fun GreetingSection() {
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

@Composable
fun ServiceButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ServiceButton("Top Doctors", Icons.Default.Person)
        ServiceButton("Pharmacy", Icons.Default.MedicalServices)
        ServiceButton("Ambulance", Icons.Default.CarCrash)
    }
}

@Composable
fun ServiceButton(text: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FilledIconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .size(80.dp)
                .padding(12.dp)
        ) {
            Icon(
                icon,
                contentDescription = text,
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
        }
        Text(text)
    }
}

@Composable
fun HealthArticles() {
    val articles = listOf(
        "The 25 Healthiest Fruits You Can Eat, According to a Nutritionist" to "Jun 10, 2023",
        "The Impact of COVID-19 on Healthcare Systems" to "Jul 10, 2023",
        "The Impact of COVID-19 on Healthcare Systems" to "Jul 10, 2023",
        "The Impact of COVID-19 on Healthcare Systems" to "Jul 10, 2023"
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Health article", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
        articles.forEach { (title, date) ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.profil),
                        contentDescription = "Article Image",
                        modifier = Modifier.size(88.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(title, fontWeight = FontWeight.Medium)
                        Text(date, style = MaterialTheme.typography.bodySmall)
                        Text("5min read", style = MaterialTheme.typography.bodySmall)
                    }
                }
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
    MedifaxTheme (darkTheme = true) {
        Surface {
            HomeScreen()
        }
    }
}