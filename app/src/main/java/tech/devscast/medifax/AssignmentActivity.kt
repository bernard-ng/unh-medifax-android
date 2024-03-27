package tech.devscast.medifax

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import tech.devscast.medifax.model.assignment.ScheduleCollection
import tech.devscast.medifax.model.assignment.ScheduleItem
import tech.devscast.medifax.model.assignment.Teacher
import tech.devscast.medifax.ui.theme.MedifaxTheme
import tech.devscast.medifax.ui.theme.poppinsFontFamily
import tech.devscast.medifax.utils.ReadJSONFromAssets

class AssignmentActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            val screens = listOf(
                Screen.Schedule,
                Screen.Teacher,
            )

            MedifaxTheme {

                context.assets.open("json.json")
                val scheduleCollection: ScheduleCollection = Json.decodeFromString<ScheduleCollection>(
                    ReadJSONFromAssets(context, "json.json")
                )

                Scaffold (
                    topBar = {
                        TopAppBar(
                            title = { Text(text = scheduleCollection.title) },
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    },
                    bottomBar = {
                        BottomAppBar (
                            actions = {
                                IconButton(onClick = { navController.navigate(Screen.Schedule.route) }) {
                                    Icon(Screen.Schedule.icon, contentDescription = "Schedule")
                                }
                                IconButton(onClick = { navController.navigate(Screen.Teacher.route) }) {
                                    Icon(Screen.Teacher.icon, contentDescription = "Teacher")
                                }
                            },
                            floatingActionButton = {},
                        )
                    }
                ) { innerPadding ->
                    NavHost(navController, startDestination = Screen.Schedule.route, Modifier.padding(innerPadding)) {
                        composable(Screen.Schedule.route) { ScheduleScreen(scheduleCollection.data) }
                        composable(Screen.Teacher.route) { TeacherScreen(scheduleCollection.teachers) }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String, val icon: ImageVector) {
    object Schedule : Screen("schedule", Icons.Filled.CalendarMonth)
    object Teacher : Screen("teacher", Icons.Filled.Person)
}

@Composable
fun TeacherScreen(
    items: List<Teacher>,
) {
    LazyColumn {
        items(items) {
            TeacherItem(teacher = it)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun TeacherItem(teacher: Teacher) {
    val context = LocalContext.current

    ElevatedCard {
        Column (
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                teacher.photo,
                contentDescription = teacher.displayName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    text = teacher.displayName,
                    fontSize = 15.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Black
                )
                Text(text = teacher.title)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Column {
                Row (
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Filled.Email, contentDescription = "email")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = teacher.mail)
                }
                Row (
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Filled.Phone, contentDescription = "phone")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = teacher.telephoneNumber)
                }
                Row (
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Filled.Home, contentDescription = "office")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = teacher.office)
                }
                Row (
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Filled.Web, contentDescription = "url")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = teacher.url)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow {
                items(teacher.affiliations) {
                    SuggestionChip(onClick = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }, label = { Text(text = it) })
                }
            }
        }
    }
}

@Composable
fun ScheduleScreen(
    items: List<ScheduleItem>,
) {
    LazyColumn {
        items(items) {
            ScheduleItemView(scheduleItem = it)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun ScheduleItemView(scheduleItem: ScheduleItem) {
    val context = LocalContext.current

    ElevatedCard {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = scheduleItem.subject,
                fontSize = 30.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Black
            )
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = scheduleItem.start)
                Text(text = scheduleItem.end)
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = scheduleItem.room)
            Text(text = scheduleItem.teacherAbbreviation)
            Text(text = scheduleItem.description)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = scheduleItem.hoursMask.toString())

            LazyRow (
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(scheduleItem.classes) {
                    SuggestionChip(onClick = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }, label = { Text(text = it) })
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GreetingPreview() {
    MedifaxTheme {
    }
}