package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.devscast.medifax.ui.theme.MedifaxTheme

@Composable
fun CameraScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.padding(20.dp),
        ) {
        }
        Button(
            onClick = { },
        ) {
            Text(text = "Click Here")
        }
    }
}

@Preview(device = "id:pixel_8_pro", showSystemUi = true, showBackground = true)
@Composable
fun PreviewCameraScreen() {
    MedifaxTheme {
        CameraScreen()
    }
}
