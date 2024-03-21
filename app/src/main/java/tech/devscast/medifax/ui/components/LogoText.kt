package tech.devscast.medifax.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.theme.poppinsFontFamily

@Composable
fun LogoText() {
    Image(
        painter = painterResource(id = R.drawable.medifax_icon),
        contentDescription = "Healthcare Logo",
        modifier = Modifier.size(120.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Medifax",
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFontFamily,
        fontSize = 30.sp,
        color = MaterialTheme.colorScheme.primary
    )
}
