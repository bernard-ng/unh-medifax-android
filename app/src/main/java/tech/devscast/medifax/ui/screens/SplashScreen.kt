package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.theme.MedifaxTheme
import tech.devscast.medifax.ui.theme.poppinsFontFamily

@Composable
fun SplashScreen() {
   Surface {
       Column (
           modifier = Modifier
               .fillMaxSize()
               .padding(16.dp),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {
           Image(
               painter = painterResource(id = R.drawable.medifax_icon),
               contentDescription = "Healthcare Logo",
               modifier = Modifier.size(120.dp)
           )
           Spacer(modifier = Modifier.height(8.dp))
           Text(
               text = "Medifax",
               fontWeight = FontWeight.Medium,
               fontFamily = poppinsFontFamily,
               fontSize = 30.sp,
               color = Color(0xFF223A6A)
           )
       }
   }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewSplashScreen() {
    MedifaxTheme {
        SplashScreen()
    }
}
