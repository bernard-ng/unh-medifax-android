package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.theme.MedifaxTheme

@Composable
fun OnboardingScreen1() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.doctor1),
                    contentDescription = "Doctor's Office",
                    modifier = Modifier.size(500.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Find a lot of specialist doctors in one place",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(50.dp)
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 25.dp)
                    .background(color = Color(0xFF407CE2), shape = MaterialTheme.shapes.medium)
            ) {
                Icon(Icons.Filled.ArrowForward, contentDescription = "Forward", tint = Color.White)
            }

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 16.dp, top = 16.dp)
            ) {
                Text(text = "Skip", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen1() {
    MedifaxTheme {
        OnboardingScreen1()
    }
}