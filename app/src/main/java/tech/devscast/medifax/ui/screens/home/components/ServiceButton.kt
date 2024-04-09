package tech.devscast.medifax.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ServiceButton(text: String, icon: ImageVector, onClick: () -> Unit ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FilledIconButton(
            onClick = { onClick() },
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
