package tech.devscast.medifax.presentation.screens.sign.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.presentation.theme.poppinsFontFamily

@Composable
fun HeadingTitle(title: String, modifier: Modifier) {
    Text(
        text = title,
        fontWeight = FontWeight.Black,
        fontFamily = poppinsFontFamily,
        fontSize = 40.sp,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(32.dp))
}
