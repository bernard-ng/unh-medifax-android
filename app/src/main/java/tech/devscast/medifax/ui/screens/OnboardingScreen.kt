package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import tech.devscast.medifax.ui.theme.blackColor
import tech.devscast.medifax.ui.theme.poppinsFontFamily
import tech.devscast.medifax.ui.theme.primaryColor

@Composable
fun HealthcareScreen() {
    Column(
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
        Spacer(modifier = Modifier.height(48.dp))
        Surface {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Let’s get started!",
                    fontWeight = FontWeight.Black,
                    fontFamily = poppinsFontFamily,
                    fontSize = 30.sp,
                    color = blackColor
                )
                Text(
                    text = "Login to Stay healthy and fit",
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        Surface(modifier = Modifier.width(300.dp)) {
            Column {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(1.dp, primaryColor),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Se connecter",
                        fontSize = 17.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = {},
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(1.dp, primaryColor),
                    colors = ButtonDefaults.buttonColors(contentColor = primaryColor, containerColor = Color.Transparent),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "S'inscrire",
                        fontSize = 17.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewHealthcareScreen() {
    MedifaxTheme {
        HealthcareScreen()
    }
}
