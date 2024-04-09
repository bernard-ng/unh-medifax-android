package tech.devscast.medifax.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import tech.devscast.medifax.navigation.Destination

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
