package tech.devscast.medifax.ui.components

import androidx.compose.material.icons.Icons
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
import androidx.navigation.NavHostController
import tech.devscast.medifax.navigation.Destination

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        NavigationBarItem(
            selected = navController.currentDestination?.route === Destination.Home.route,
            onClick = { navController.navigate(Destination.Home.route) },
            icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
            label = { Text("Accueil") }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route === Destination.Profile.route,
            onClick = { navController.navigate(Destination.Profile.route) },
            icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
            label = { Text("Profil") }
        )
    }
}
