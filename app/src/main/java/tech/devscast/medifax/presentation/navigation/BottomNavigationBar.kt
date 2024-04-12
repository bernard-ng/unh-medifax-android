package tech.devscast.medifax.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = getBottomNavigationDestinations()

    NavigationBar {
        items.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == destination.route,
                onClick = {
                    selectedItem = index
                    navController.navigate(destination.route)
                },
                icon = { destination.icon?.let { Icon(it, contentDescription = null) } },
                label = { Text(destination.label) }
            )
        }
    }
}
