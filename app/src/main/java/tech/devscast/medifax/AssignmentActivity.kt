package tech.devscast.medifax

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import tech.devscast.medifax.ui.theme.MedifaxTheme

class AssignmentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MedifaxTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val title = remember { mutableStateOf("Medifax") }

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            DrawerHeader()
                            DrawerBody(
                                items = listOf(
                                    MenuItem(
                                        id = "A",
                                        title = "View A",
                                        contentDescription = "Go to view A screen",
                                        icon = Icons.Default.Home
                                    ),
                                    MenuItem(
                                        id = "B",
                                        title = "View B",
                                        contentDescription = "Go to view B screen",
                                        icon = Icons.Default.Settings
                                    )
                                ),
                                onItemClick = {
                                    navController.navigate(it.id)
                                    title.value = it.title
                                }
                            )
                        }
                    },
                ) {
                    Scaffold(
                        topBar = {
                            AppBar(
                                onNavigationIconClick = {
                                    scope.launch {
                                        if (drawerState.isOpen) drawerState.close() else drawerState.open()
                                    }
                                },
                                title = title.value
                            )
                        }
                    ) {innerPadding ->
                        SetupNavGraph(
                            navController = navController,
                            startDestination = "A",
                            padding = innerPadding
                        )
                    }
                }
            }
        }
    }
}


data class MenuItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)

data class Country(
    val id: String,
    val name: String,
    val flag: String,
    val population: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit,
    title: String
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer"
                )
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
       items(items) {item ->
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .clickable {
                       onItemClick(item)
                   }
                   .padding(16.dp)
           ) {
               Icon(
                   imageVector = item.icon,
                   contentDescription = item.contentDescription
               )
               Spacer(modifier = Modifier.width(16.dp))
               Text(
                   text = item.title,
                   style = itemTextStyle,
                   modifier = Modifier.weight(1f)
               )
           }
       }
    }
}

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
    padding: PaddingValues
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("A") {
            ViewA(
                onButtonClick = {
                    navController.navigate("B")
                },
                padding
            )
        }
        composable("B") {
            ViewB(
                onButtonClick = {
                    navController.navigate("A")
                },
                padding
            )
        }
    }
}

@Composable
fun ViewA(
    onButtonClick: () -> Unit,
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        val items = listOf(
            Country("CD", "RD Congo", "https://flagsapi.com/CD/flat/64.png", 2000),
            Country("US", "United States", "https://flagsapi.com/US/flat/64.png", 1000),
            Country("BE", "Belgium", "https://flagsapi.com/BE/flat/64.png", 2000),
            Country("SA", "South Africa", "https://flagsapi.com/ZA/flat/64.png", 2000)
        )

        LazyColumn (
            modifier = Modifier.padding(20.dp)
        ) {
            items(items) {country ->
                ElevatedCard (
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                Toast.makeText(context, "Population : ${country.population}", Toast.LENGTH_SHORT).show()
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(),
                        )
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        AsyncImage(
                            country.flag,
                            contentDescription = country.name,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.CenterVertically)
                                .padding(start = 10.dp)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = country.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                            Text(
                                text = "Population : ${country.population.toString()}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box (
            modifier = Modifier.padding(10.dp),
        ) {
            Button(
                onClick = { onButtonClick() },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Visit View B",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ViewB(
    onButtonClick: () -> Unit,
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { onButtonClick() },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Visit View A",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GreetingPreview() {
    MedifaxTheme {
    }
}