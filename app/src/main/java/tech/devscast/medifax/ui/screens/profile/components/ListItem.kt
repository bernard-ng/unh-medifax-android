package tech.devscast.medifax.ui.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { onClick() })
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(onClick = { onClick() }) {
            Icon(icon, contentDescription = null)
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(text, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, contentDescription = null)
    }
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
}
